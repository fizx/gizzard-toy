package com.kylemaxwell.toy

import java.io._
import ext._

case class Put(key: String, value: String) extends StandardJob[Shard] {
  def apply(service: ToyService) = service._put(key, value)
}