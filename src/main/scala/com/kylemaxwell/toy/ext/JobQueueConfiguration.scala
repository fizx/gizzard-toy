package com.kylemaxwell.toy.ext

import net.lag.kestrel.{PersistentQueue, QItem}
import com.twitter.gizzard.scheduler._
import com.twitter.gizzard._

trait JobQueueConfiguration[S <: shards.Shard] {
  def name(): String
  def queue(): PersistentQueue
  def codec(): CodecFactory[S]
}