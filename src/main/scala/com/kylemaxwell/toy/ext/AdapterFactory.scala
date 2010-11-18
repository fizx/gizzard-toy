package com.kylemaxwell.toy.ext

import com.twitter.gizzard.shards._

trait AdapterFactory[S <: Shard] extends ((ReadWriteShard[S]) => S)