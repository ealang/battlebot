import org.scalatest.{Matchers, WordSpec}
import com.jhood.battlebot._

class TestFormationCalc extends WordSpec with Matchers {
  "A FormationCalc" should {
    "detect a battalion and return a 300 score with valueless cardss" in {
      val cards = List(Card("g", 0), Card("g", 0), Card("g", 0 ))
      FormationCalc.score(cards) should be (300)
    }    
    
    "detect a battalion and return a 3xx score with valued cards" in {
      val cards = List(Card("g", 1), Card("g", 2), Card("g", 3))
      FormationCalc.score(cards) should be (306)
    }    
  }
}

