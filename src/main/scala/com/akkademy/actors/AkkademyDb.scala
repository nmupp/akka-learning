package com.akkademy.actors

import akka.actor.Actor
import com.akkademy.messages.SetRequest
import akka.event.Logging
import scala.collection.mutable.HashMap

/**
  * Created by nmupp on 3/13/16.
  */
class AkkademyDb extends Actor {

  val map = new HashMap[String, Object]
  val log = Logging(context.system, this)

  override def receive = {
    case SetRequest(key, value) => {
      log.info("request received => key: {}, value: {}", key, value)
      map.put(key, value)
    }
    case o => log.info("received a message which is unknown {}", o)
  }
}
