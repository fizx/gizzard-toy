package com.kylemaxwell.toy

import java.io._
import ext._
import com.twitter.gizzard.scheduler.Job

case class Put(key: String, value: String) extends PJob {
  def apply(service: ToyService) = service.putNow(key, value)
}