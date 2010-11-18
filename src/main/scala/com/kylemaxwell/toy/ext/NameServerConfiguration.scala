package com.kylemaxwell.toy.ext

import com.twitter.gizzard.nameserver._
import com.twitter.gizzard._

abstract class NameServerConfiguration[S <: shards.Shard](val adapter: AdapterFactory[S]) {
  
  var shardTypes = Seq[(String, shards.ShardFactory[S])]()
  
  def shard(): Shard
  def mappingFunction: (Long) => Long
  def addShardType(stype: (String, shards.ShardFactory[S])) = shardTypes ++ Seq(stype)
}