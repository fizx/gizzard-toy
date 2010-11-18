package com.kylemaxwell.toy.ext

import com.twitter.gizzard._
import com.twitter.xrayspecs.Duration

trait SchedulerConfiguration[S <: shards.Shard] {
  def maxRetries: Int
  def threadCount: Int
  def retryInterval: Duration
  def name: String
  def normalQueue: JobQueueConfiguration[S]
  def errorQueue: JobQueueConfiguration[S]
}