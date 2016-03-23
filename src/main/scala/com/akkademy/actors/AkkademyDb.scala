package com.akkademy.actors

import akka.actor.{Status, Actor}
import com.akkademy.messages.{KeyNotFoundException, GetRequest, SetRequest}
import akka.event.Logging
import scala.collection.mutable.HashMap

/**
  * Created by nmupp on 3/13/16.
  */
class AkkademyDb(arg:String) extends Actor {

  val map = new HashMap[String, Object]
  val log = Logging(context.system, this)

  override def receive = {
    case SetRequest(key, value) => {
      log.info("request received => key: {}, value: {}, arg: {}", key, value, arg)
      map.put(key, value)
      log.info(s"SENDER PATH===>${sender.path}")
      sender() ! Status.Success
    }
    case GetRequest(key) => {
      map.get(key) match {
        case response:Object => sender() ! response
        case None => sender() ! Status.Failure(new KeyNotFoundException(key))
      }
    }
    case o => log.info("received a message which is unknown {}", o)
  }
}
