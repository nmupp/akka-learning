package com.akkademy

import akka.actor.{Props, ActorSystem}
import akka.testkit.TestActorRef
import akka.util.Timeout
import com.akkademy.actors.AkkademyDb
import com.akkademy.messages.SetRequest
import org.scalatest.{BeforeAndAfterEach, Matchers, FunSpecLike}
import scala.concurrent.duration._
import akka.pattern.ask
import scala.concurrent.ExecutionContext.Implicits.global
/**
  * Created by nmupp on 3/13/16.
  */
class AkkademyDbSpec extends FunSpecLike with Matchers with BeforeAndAfterEach {
  implicit val system = ActorSystem()

  describe("akkademyDb") {
    describe("given SetRequest") {
      it("should place key/value in map") {
        val actorRef = TestActorRef(new AkkademyDb("sf"))
        actorRef ! SetRequest("Narsi","PranuBunnu")
        actorRef ! SetRequest("Pranu","NarsiBunnu")
        val akkademyDb = actorRef.underlyingActor
        akkademyDb.map.get("Narsi") should equal(Some("PranuBunnu"))
        akkademyDb.map.get("Pranu") should equal(Some("NarsiBunnu"))
      }

      it("This is my test") {
        implicit val timeOut = Timeout(3 seconds)
        val actorRef = system.actorOf(Props(classOf[AkkademyDb], "NarsiPranuBunnu"))
        val future = actorRef ? SetRequest("Sarada", "Mama")
//        val result = Await.result(future.mapTo[String], 3 second)
        future.onSuccess({
          case x:String => println(x)
        })
        future.recoverWith({
          case ex:Exception => actorRef ? SetRequest("fs","fsj")
        })
        Thread.sleep(4000)
      }
    }
  }
}
