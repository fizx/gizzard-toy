package com.kylemaxwell.toy.ext

import com.twitter.gizzard.nameserver._

class Service[S <: shards.Shard](config: GizzardConfiguration) {
  val nameServer: NameServer[S] = new ConfiguredNameServer[S](config.nameServer)
  
}