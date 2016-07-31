import org.scalatest.{Matchers, WordSpec}
import com.jhood.battlebot._

class TestFormationCalc extends WordSpec with Matchers {
  "A FormationCalc" should {
    "detect a wedge and return a 5xx score" in {
      val cards = List(Card("a", 1), Card("a", 2), Card("a", 3))
      FormationCalc.score(cards) should be (506)
    }

    "detect a phallanx and return a 4xx score" in {
      val cards = List(Card("a", 1), Card("b",1), Card("c",1))
      FormationCalc.score(cards) should be (403)
    }
    
    "detect a battalion and return a 3xx score with valued cards" in {
      val cards = List(Card("g", 1), Card("g", 8), Card("g", 5))
      FormationCalc.score(cards) should be (314)
    }

    "detect a skirmish and return a 2xx score" in {
      val cards = List(Card("a", 1), Card("b",2), Card("c",3))
      FormationCalc.score(cards) should be (206)
    }

    "return the sum of cards when less than 3 are provided" in {
      val cards = List(Card("a",1), Card("b",1))
      FormationCalc.score(cards) should be (2)
    }

    "return 0 for an empty list" in {
      FormationCalc.score(List()) should be (0)
    }
  }
}

