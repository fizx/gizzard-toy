package com.kylemaxwell.toy

import ext._
import com.twitter.gizzard.scheduler.Job

case class Delete(key: String) extends PJob {
  def apply(service: ToyService) = service.deleteNow(key)
}