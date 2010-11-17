package com.kylemaxwell.toy

import org.apache.http.client._

class RemoteShard(host: String) extends Shard {
  // val client = new HttpClient
  
  def query(q: String, local: Boolean): Seq[String]
  
  def put(key: String, value: String): Unit
  
  def get(key: String): String
  
  def delete(key: String): Unit
}