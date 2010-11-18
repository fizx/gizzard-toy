package com.kylemaxwell.toy.ext

import com.twitter.gizzard.scheduler.Job

object PJob {
  def inflate(s: Any, bytes: Array[Byte]) = new PJob()
}

class PJob extends Job {
  def deflate(): Array[Byte] = "".getBytes
  def apply() = {}
}