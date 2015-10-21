import org.scalatest.{Matchers, WordSpec}
import com.jhood.battlebot._

class TestMessageParser extends WordSpec with Matchers {

  "A MessageParser" should {
    "parses name request messages" in {
      MessageParser("player north name") should be (NameRequest(North))
      MessageParser("player south name") should be (NameRequest(South))
    }

    "parse color declarations" in {
      MessageParser("colors color1 color2 color3 color4 color5 color6") should be (
        ColorsDeclaration(List(
          "color1", "color2", "color3", "color4", "color5", "color6"
        ))
      )
    }

    "parse player north hand" in {
      MessageParser("player north hand color1,1 color2,2") should be (
        PlayerHand(North, List(Card("color1",1), Card("color2",2)))
      )
    }

    "parse player south hand" in {
      MessageParser("player south hand color1,1 color2,2") should be (
        PlayerHand(South, List(Card("color1",1), Card("color2",2)))
      )
    }

    "parse claim status message" in {
      MessageParser("flag claim-status unclaimed north south unclaimed north south unclaimed north south") should be (
        FlagClaimStatus(List(
          None, Some(North), Some(South), None, Some(North), Some(South), None, Some(North), Some(South)
        ))
      )
    }

    "parse flag without cards" in {
      MessageParser("flag 1 cards south") should be (
        FlagCards(1, South, List())
      )
    }

    "parse flag with cards" in {
      MessageParser("flag 1 cards south color1,1 color2,2") should be (
        FlagCards(1, South, List(
          Card("color1",1),
          Card("color2",2)
        ))
      )
    }

    "parse opponent plays" in {
      MessageParser("opponent play 1 color3,4") should be (
        OpponentPlay(1,Card("color3", 4))
      )
    }
  }
}
