package com.kylemaxwell.toy

import java.io._
import ext._
// import com.twitter.gizzard.nameserver._

class ToyService(config: GizzardConfiguration[Shard]) extends Service[Shard](config) with Shard {
  val DEFAULT = 0
  
  def query(q: String, local: Boolean): Seq[String] = {
    Seq()
  }
  
  def put(key: String, value: String): Unit = scheduler(Put(key, value))
  
  def putNow(key: String, value: String) = findCurrentForwarding(DEFAULT, hash(key)).put(key, value)
  
  def get(key: String): String = findCurrentForwarding(DEFAULT, hash(key)).get(key)
  
  def delete(key: String): Unit = scheduler(Delete(key))
  
  def deleteNow(key: String): Unit = findCurrentForwarding(DEFAULT, hash(key)).delete(key)
  
  private def hash(key: String): Int = {
    return key.hashCode
  }
}