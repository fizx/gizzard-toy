package com.kylemaxwell.toy.ext

import com.twitter.gizzard.shards

trait StandardShard extends Shard {
  def initialize()
}