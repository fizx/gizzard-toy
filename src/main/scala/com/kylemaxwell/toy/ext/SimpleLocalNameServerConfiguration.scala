package com.kylemaxwell.toy.ext

import com.twitter.gizzard.nameserver._
import com.twitter.gizzard._
import com.twitter.querulous.evaluator._
import net.lag.configgy._

class SimpleLocalNameServerConfiguration[S <: shards.Shard](db: String, adapter: AdapterFactory[S]) extends NameServerConfiguration[S](adapter) {
  def shard() = {
    val config = Config.fromMap(Map())
    val queryEvaluatorFactory = QueryEvaluatorFactory.fromConfig(config, None)
    new SqlShard(queryEvaluatorFactory(db, "root", ""))
  }
  
  def mappingFunction = { input: Long => input }
}