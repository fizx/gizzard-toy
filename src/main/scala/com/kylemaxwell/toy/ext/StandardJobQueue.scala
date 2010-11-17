package com.kylemaxwell.toy.ext

import com.twitter.gizzard.scheduler._
import net.lag.kestrel._
import com.twitter.gizzard.scheduler._

object StandardJobQueue {
  def apply[J](config: JobQueueConfiguration[J]) = {
    new StandardJobQueue[J](config.name, config.queue, config.codec)
  }
}

class StandardJobQueue[J <: Job](name: String, queue: PersistentQueue, codec: Codec[J]) extends KestrelJobQueue[J](name, queue, codec) {
  
}