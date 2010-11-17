package com.kylemaxwell.toy.ext

import com.twitter.gizzard._
import com.twitter.xrayspecs.Duration

class SimpleLocalSchedulerConfiguration(path: String) extends SchedulerConfiguration {
  def maxRetries = 100
  def threadCount = 1
  def retryInterval = 15.minutes
  def name = "default"
  def normalQueue: JobQueueConfiguration
  def errorQueue: JobQueueConfiguration
}