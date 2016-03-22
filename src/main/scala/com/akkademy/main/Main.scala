package com.akkademy.main

import java.io.File

import akka.actor.{Props, ActorSystem}
import com.akkademy.actors.AkkademyDb
import com.typesafe.config.ConfigFactory

/**
  * Created by nmupp on 3/17/16.
  */
object Main extends App {
  val configFile = getClass.getClassLoader.getResource("application.conf").getFile
  val config = ConfigFactory.parseFile(new File(configFile))
  val actorSystem = ActorSystem("akkademy",config)
  val actorRef = actorSystem.actorOf(Props(classOf[AkkademyDb], "Its fine!!"),name = "akkademy-db")
}
