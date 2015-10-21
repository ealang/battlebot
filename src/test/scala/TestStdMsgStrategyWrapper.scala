import org.scalatest.{Matchers, WordSpec}
import com.jhood.battlebot._

import scala.concurrent.Future

class NullStrategy extends Strategy {
  def update(msg: GameMessage) = Future.successful(None)
}

class TalkativeStrategy extends Strategy {
  def update(msg: GameMessage) = Future.successful(Some(PlayCardResponse(1, Card("red",3))))
}

class TestStdMsgStrategyWrapper extends WordSpec with Matchers {
  "A StdMsgStrategyWrapper" should {
    "receive none if the strategy doesn't response" in {
      val wrapper = new StdMsgStrategyWrapper(new NullStrategy())
      wrapper.update("go play-card") should be (None)
    }

    "receive some message if the strategy responds" in {
      val wrapper = new StdMsgStrategyWrapper(new TalkativeStrategy())
      wrapper.update("go play-card") should be (Some("play 1 red,3"))
    }

    "receive None if the request could not be parsed" in {
      val wrapper = new StdMsgStrategyWrapper(new TalkativeStrategy())
      wrapper.update("kittens") should be (None)
    }
  }
}
