package com.kylemaxwell.toy

import ext._

case class Delete(key: String) extends StandardJob[Shard] {
  def apply(service: ToyService) = service._delete(key)
}