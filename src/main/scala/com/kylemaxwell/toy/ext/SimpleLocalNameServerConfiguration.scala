package com.kylemaxwell.toy.ext

class SimpleLocalNameServerConfiguration[T] extends NameServerConfiguration[T] {
  def mappingFunction = { input: Long => input }
}