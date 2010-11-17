package com.kylemaxwell.toy.ext

import com.twitter.gizzard.nameserver._

class NameServerConfiguration[T] {
  def shard: Shard
  def shardRepository: ShardRepository[T]
  def mappingFunction: (Long) => Long
}