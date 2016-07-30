import org.scalatest.{Matchers, WordSpec}
import com.jhood.battlebot._
import scala.concurrent.Await
import scala.concurrent.duration._

class TestRandomStrategy extends WordSpec with Matchers {
  val strategy = new RandomStrategy()

  def assertRequestResponse(request: GameMessage, response: ResponseMessage) = {
    val result = Await.result(strategy.update(request), 1 second)
    result should be (Some(response))
  }

  def assertRequestNoResponse(request: GameMessage) = {
    val result = Await.result(strategy.update(request), 1 second)
    result should be (None)
  }

  "A RandomStrategy" should {
    "respond with a name" in {
      assertRequestResponse(
        NameRequest(North),
        NameSet(North, "darthbagel")
      )
    }

    "respond with a dummy play" in {
      assertRequestResponse(
        PlayCard(),
        PlayCardResponse(1, Card("color1",1))
      )
    }

    "not respond to all other messages" in {
      assertRequestNoResponse(OpponentPlay(1, Card("color1",1)))
      assertRequestNoResponse(FlagCards(1, North, List()))
      assertRequestNoResponse(FlagClaimStatus(List()))
      assertRequestNoResponse(PlayerHand(North,List()))
      assertRequestNoResponse(ColorsDeclaration(List()))
    }
  }

}
