package com.kylemaxwell.toy.ext

import com.twitter.gizzard.scheduler._

case class StandardJob[Service]() extends JsonJob {
  def toMap =
    (Map[String, Any]() /: this.getClass.getDeclaredFields) {(a, f) =>
      f.setAccessible(true)
      a + (f.getName -> f.get(this))
    }  
    
  def apply(s: Service)
}

