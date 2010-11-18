package com.kylemaxwell.toy.ext

import com.twitter.gizzard.shards._

abstract class GizzardConfiguration[S <: Shard] {
  def nameServer: NameServerConfiguration[S]
  def scheduler: SchedulerConfiguration[S]
  def addShardType(a: (String, ShardFactory[S])) = nameServer.addShardType(a)
}