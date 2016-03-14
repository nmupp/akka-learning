package com.akkademy

import akka.actor.ActorSystem
import akka.testkit.TestActorRef
import com.akkademy.actors.AkkademyDb
import com.akkademy.messages.SetRequest
import org.scalatest.{BeforeAndAfterEach, Matchers, FunSpecLike}

/**
  * Created by nmupp on 3/13/16.
  */
class AkkademyDbSpec extends FunSpecLike with Matchers with BeforeAndAfterEach {
  implicit val system = ActorSystem()

  describe("akkademyDb") {
    describe("given SetRequest") {
      it("should place key/value in map") {
        val actorRef = TestActorRef(new AkkademyDb)
        actorRef ! SetRequest("Narsi","PranuBunnu")
        actorRef ! SetRequest("Pranu","NarsiBunnu")
        val akkademyDb = actorRef.underlyingActor
        akkademyDb.map.get("Narsi") should equal(Some("PranuBunnu"))
        akkademyDb.map.get("Pranu") should equal(Some("NarsiBunnu"))
      }
    }
  }
}
