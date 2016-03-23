package com.akkademy.main

import java.io.File

import akka.actor.{Props, ActorSystem}
import com.akkademy.actors.AkkademyDb
import com.typesafe.config.ConfigFactory

/**
  * Created by nmupp on 3/17/16.
  */
object Main extends App {
  val config = ConfigFactory.load.getConfig("AkkademyService")
  val actorSystem = ActorSystem("AkkademyService",config)
  val actorRef = actorSystem.actorOf(Props(classOf[AkkademyDb], "Its fine!!"),"remote-worker")

  println(s"Workers path ${actorRef.path}")
}
