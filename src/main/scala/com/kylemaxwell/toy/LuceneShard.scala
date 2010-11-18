package com.kylemaxwell.toy

import org.apache.lucene.index._
import org.apache.lucene.document._
import org.apache.lucene.search._
import org.apache.lucene.store._
import org.apache.lucene.analysis._
import org.apache.lucene.analysis.standard._
import org.apache.lucene.util._
import org.apache.lucene.queryParser._
import java.io._
import com.twitter.gizzard.shards.ShardInfo

class LuceneShardFactory(host: String) extends com.twitter.gizzard.shards.ShardFactory[Shard] {
  
  def instantiate(shardInfo: ShardInfo, weight: Int, children: Seq[Shard]): Shard = {
    new LuceneShard(shardInfo, weight, children)
  } 
  
  def materialize(shardInfo: ShardInfo) = {
    new LuceneShard(shardInfo, 0, Seq()).initialize()
  }
}

class LuceneShard(val shardInfo: ShardInfo, val weight: Int, val children: Seq[Shard]) extends toy.Shard {
  val path = shardInfo.tablePrefix
  var dir = FSDirectory.open(new File(path))
  var reader = new IndexSearcher(dir)
  val analyzer = new StandardAnalyzer(Version.LUCENE_CURRENT)
  var writer = new IndexWriter(dir, analyzer, IndexWriter.MaxFieldLength.UNLIMITED)
  val doc = new Document()
  val keyField = new Field("key", "".getBytes, Field.Store.YES)
  val valField = new Field("text", "".getBytes, Field.Store.YES)
  doc.add(keyField)
  doc.add(valField)
  var parser = new QueryParser(Version.LUCENE_CURRENT, "text", analyzer)
  
  def initialize() = {}
  
  def query(q: String, local: Boolean)  = { 
    val docs = reader.search(parser.parse(q), 100)
    docs.scoreDocs.map{ doc => reader.doc(doc.doc).get("key") }
  }
  
  def put(key: String, value: String) = {
    keyField.setValue(key)
    valField.setValue(value)
    writer.addDocument(doc)
    save
  }
  def putNow = put
  
  def delete(key: String) =  {
    writer.deleteDocuments(new TermQuery(new Term("key", key)))
    save
  }
  private def save = {
    writer.commit
    reader = new IndexSearcher(dir)
  }
}