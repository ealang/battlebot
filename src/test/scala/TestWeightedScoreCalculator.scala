import org.scalatest.{Matchers, WordSpec}
import com.jhood.battlebot._

class TestWightedScoreCalculator extends WordSpec with Matchers  {
  val calc = new WeightedScoreCalculator

  "A highest score calculator" should {
    "return a default move if none are available" in {
      calc.compute_play(List(), Map(), Map(), Map()) should be (PlayCardResponse(1, Card("color1",1)))
    }

    "trivially select a high-value move on a populated flag" in {
      val flags = Map(1 -> List(Card("color1", 1), Card("color2", 2)), 2 -> List())
      val hand = List(Card("color1", 3), Card("color2", 1))
      calc.compute_play(hand, flags, Map(), Map()) should be (PlayCardResponse(1, Card("color1", 3)))
    }

    "select the highest potential play when flags aren't complete" in {
      val flags = Map(1 -> List(Card("color1", 1)), 2 -> List())
      val hand = List(Card("color1", 3), Card("color1", 2), Card("color2", 1))
      calc.compute_play(hand, flags, Map(), Map()) should be (PlayCardResponse(1, Card("color1", 3)))
    }

    "prefer to play in a highly weighted location" in {
      val flags = Map(1 -> List(), 2 -> List())
      val hand = List(Card("color1", 1), Card("color1", 2), Card("color1", 3))
      calc.compute_play(hand, flags, Map(), Map()) should be (PlayCardResponse(2, Card("color1", 1))) 
    }
  }
}
