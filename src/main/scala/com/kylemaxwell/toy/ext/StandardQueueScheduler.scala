package com.kylemaxwell.toy.ext

import com.twitter.gizzard.scheduler._
import net.lag.kestrel.PersistentQueue
import com.twitter.xrayspecs.Duration
import net.lag.configgy.ConfigMap
import net.lag.configgy.Config
import com.twitter.xrayspecs.TimeConversions._

object StandardQueueScheduler {
  def apply[T](service: T, config: SchedulerConfiguration): StandardQueueScheduler[T] = {
    val normal = StandardJobQueue(config.normalQueue)
    val errors = StandardJobQueue(config.errorQueue)
    new StandardQueueScheduler(service, config.name, config.threadCount, config.retryInterval, config.maxRetries, 
        normal, errors, None)
  }
}

class StandardQueueScheduler[ServiceClass](val service: ServiceClass, 
                             val name: String,
                             val threadCount: Int,
                             val retryInterval: Duration,
                             val errorLimit: Int,
                             val queue: JobQueue[Job],
                             val errorQueue: JobQueue[Job],
                             val badJobQueue: Option[JobConsumer[Job]]) extends JobScheduler[Job](name, threadCount, retryInterval, errorLimit, queue, errorQueue, badJobQueue) {
                               
  def apply(job: StandardJob[ServiceClass]) = put(job)
  
  def started() = {
    start()
    this
  }
  
}