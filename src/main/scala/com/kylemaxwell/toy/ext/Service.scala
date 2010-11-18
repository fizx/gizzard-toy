package com.kylemaxwell.toy.ext

import com.twitter.gizzard.nameserver._
import com.twitter.gizzard._

abstract class Service[S <: shards.Shard](val config: GizzardConfiguration[S]) {
  self: S => 
    val children = Seq[S]()
    val weight = 1
    val shardInfo = new shards.ShardInfo(getClass.getName, "service", "localhost")
    val nameServer: NameServer[S] = new ConfiguredNameServer[S](config.nameServer)
    val scheduler = StandardQueueScheduler(this, config.scheduler)
  
    def findCurrentForwarding(a: Int, b: Long) = nameServer.findCurrentForwarding(a, b)
  
    def start() = scheduler.start()
  
    def started() = {
      start()
      this
    }
  
}