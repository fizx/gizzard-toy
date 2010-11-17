package com.kylemaxwell.toy

import com.twitter.gizzard._

trait Shard extends shards.Shard {
  def query(q: String, local: Boolean): Seq[String]
  
  def put(key: String, value: String): Unit

  def putLater(key: String, value: String): Unit
  
  def get(key: String): String
  
  def delete(key: String): Unit
  
  def deleteLater(key: String): Unit
}