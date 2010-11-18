package com.kylemaxwell.toy.ext

import com.twitter.gizzard._

class SimpleLocalGizzardConfiguration[S <: shards.Shard](db: String, path: String, adapter: AdapterFactory[S]) extends GizzardConfiguration[S] {
  def scheduler = new SimpleLocalSchedulerConfiguration[S](path)
  def nameServer = new SimpleLocalNameServerConfiguration[S](db, adapter)
}