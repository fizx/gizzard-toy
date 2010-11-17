package com.kylemaxwell.toy.ext

import com.twitter.gizzard.nameserver._
import com.twitter.gizzard.shards._
import com.twitter.gizzard._

class ConfiguredNameServer[T <: shards.Shard](
             shard: nameserver.Shard, 
             repo: ShardRepository[T], 
             mapper:(Long) => Long) 
             extends NameServer[T](shard, repo, mapper) {
  def this(config: NameServerConfiguration[T]) = this(config.shard, config.shardRepository, config.mappingFunction)
  
  def addShardType(shard: (String, ShardFactory[T])) = repo += shard
}