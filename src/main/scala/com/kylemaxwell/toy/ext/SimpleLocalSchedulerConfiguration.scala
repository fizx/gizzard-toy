package com.kylemaxwell.toy.ext

import com.twitter.gizzard._
import com.twitter.gizzard.scheduler._
import com.twitter.xrayspecs.TimeConversions._
import com.twitter.xrayspecs.Duration

class SimpleLocalSchedulerConfiguration[S <: shards.Shard](path: String) extends SchedulerConfiguration[S] {
  def maxRetries = 100
  def threadCount = 1
  def retryInterval = 15.minutes
  def name = "default"
  def normalQueue: JobQueueConfiguration[S] = new SimpleLocalJobQueueConfiguration[S](path, "normal")
  def errorQueue: JobQueueConfiguration[S] = new SimpleLocalJobQueueConfiguration[S](path, "error")
}