
import org.scalatest.{Matchers, WordSpec}
import com.jhood.battlebot._

class TestResponseSerializer extends WordSpec with Matchers {
  "A  ResponseSerializer" should {
    "serialize a set name meassage" in {
      ResponseSerializer(NameSet(North, "foo")) should be ("player north foo")
    }

    "serialize a play card response" in {
      ResponseSerializer(PlayCardResponse(2, Card("red", 1))) should be ("play 2 red,1")
    }
  }
}
