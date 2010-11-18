package com.kylemaxwell.toy.ext

import com.twitter.gizzard.scheduler._
import com.twitter.gizzard._

class BrokenCodecFactory[S <: shards.Shard] extends CodecFactory[S]{
  def apply(s: S): Codec[PJob] = new BrokenCodec[S](s)
}

class BrokenCodec[S <: shards.Shard](s: S) extends Codec[PJob] {

  def flatten(job: PJob): Array[Byte] = job.deflate

  def inflate(data: Array[Byte]) = PJob.inflate(s, data)
}