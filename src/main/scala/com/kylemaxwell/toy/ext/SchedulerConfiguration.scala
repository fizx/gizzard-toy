package com.kylemaxwell.toy.ext

import com.twitter.gizzard._
import com.twitter.xrayspecs.Duration

class SchedulerConfiguration {
  def maxRetries: Int
  def threadCount: Int
  def retryInterval: Duration
  def name: String
  def normalQueue: JobQueueConfiguration
  def errorQueue: JobQueueConfiguration
}