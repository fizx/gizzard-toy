package com.kylemaxwell.toy.ext

import com.twitter.gizzard.shards._
import com.twitter.gizzard.scheduler._

trait CodecFactory[S <: Shard] {
  def apply(s: S): Codec[PJob]
}