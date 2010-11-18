package com.kylemaxwell.toy.ext

import com.twitter.gizzard.scheduler._
import com.twitter.gizzard._
import net.lag.kestrel.PersistentQueue
import com.twitter.xrayspecs.Duration
import net.lag.configgy.ConfigMap
import net.lag.configgy.Config
import com.twitter.xrayspecs.TimeConversions._

object StandardQueueScheduler {
  def apply[T <: shards.Shard](service: T, config: SchedulerConfiguration[T]): StandardQueueScheduler[T] = {
    val normal = StandardJobQueue(service, config.normalQueue)
    val errors = StandardJobQueue(service, config.errorQueue)
    new StandardQueueScheduler(service, config.name, config.threadCount, config.retryInterval, config.maxRetries, 
        normal, errors, None)
  }
}

class StandardQueueScheduler[ServiceClass](val service: ServiceClass, 
                             name: String,
                             threadCount: Int,
                             retryInterval: Duration,
                             errorLimit: Int,
                             queue: JobQueue[PJob],
                             errorQueue: JobQueue[PJob],
                             badJobQueue: Option[JobConsumer[PJob]]) extends JobScheduler[PJob](name, threadCount, retryInterval, errorLimit, queue, errorQueue, badJobQueue) {
                               
  def apply(job: PJob) = put(job)
  
  def started() = {
    start()
    this
  }
  
}