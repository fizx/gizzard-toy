package com.kylemaxwell.toy.ext

import com.twitter.gizzard.scheduler._
import net.lag.kestrel._
import com.twitter.gizzard._

object StandardJobQueue {
  def apply[S <: shards.Shard](s: S, config: JobQueueConfiguration[S]) = {
    new StandardJobQueue[PJob](config.name, config.queue, config.codec()(s))
  }
}

class StandardJobQueue[J <: PJob](name: String, queue: PersistentQueue, codec: Codec[J]) extends KestrelJobQueue[J](name, queue, codec) {
  
}