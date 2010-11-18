package com.kylemaxwell.toy.ext

import net.lag.kestrel.{PersistentQueue, QItem}
import com.twitter.gizzard.scheduler._
import net.lag.configgy._
import com.twitter.gizzard._

class SimpleLocalJobQueueConfiguration[S <: shards.Shard](path: String, name: String) extends JobQueueConfiguration[S] {
  def name(): String  = name
  def queue(): PersistentQueue = new PersistentQueue(path, name, Config.fromMap(Map()))
  def codec(): CodecFactory[S] = new BrokenCodecFactory[S]
}