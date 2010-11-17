package com.kylemaxwell.toy

import com.twitter.gizzard._
import com.twitter.gizzard.shards._

class Adapter(shard: ReadWriteShard[Shard]) extends shards.ReadWriteShardAdapter(shard) with Shard {
  def query(q: String, local: Boolean): Seq[String]    = shard.readOperation(_.query(q, local))
  def put(key: String, value: String): Unit            = shard.writeOperation(_.put(key, value))
  def putLater(key: String, value: String): Unit       = shard.writeOperation(_.putLater(key, value))
  def get(key: String): String                         = shard.readOperation(_.get(key))
  def delete(key: String): Unit                        = shard.writeOperation(_.delete(key))
  def deleteLater(key: String): Unit                   = shard.writeOperation(_.deleteLater(key))
}