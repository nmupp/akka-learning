package com.akkademy.main

import akka.actor.{Props, ActorSystem}
import com.akkademy.actors.AkkademyDb

/**
  * Created by nmupp on 3/17/16.
  */
object Main extends App {
  val actorSystem = ActorSystem("akkademy")
  val actorRef = actorSystem.actorOf(Props(classOf[AkkademyDb], "Its fine!!"),name = "akkademy-db")
}
