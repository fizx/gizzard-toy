package com.kylemaxwell.toy

import org.apache.http.client._

abstract class RemoteShard(host: String) extends Shard {
  // val client = new HttpClient
  
  def query(q: String, local: Boolean): Seq[String]
  
  def put(key: String, value: String): Unit
  
  def get(key: String): Option[String]
  
  def delete(key: String): Unit
}