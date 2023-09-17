import org.scalatest._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers
import Challenges._

class ChallengesSpec extends AnyFreeSpec with Matchers {
  "calculateSum" - {
    "returns 0 for an empty list" in {
      val numbers = List.empty
      calculateSum(numbers) shouldEqual 0
    }

    "returns the correct sum for a list of positive numbers" in {
      val numbers = List(1, 2, 3, 4, 5)
      calculateSum(numbers) shouldEqual 15
    }

    "returns the correct sum for a list of negative numbers" in {
      val numbers = List(-1, -2, -3, -4, -5)
      calculateSum(numbers) shouldEqual -15
    }

    "returns the correct sum for a list with mixed positive and negative numbers" in {
      val numbers = List(-1, 2, -3, 4, -5)
      calculateSum(numbers) shouldEqual -3
    }
  }

  "filterAndConvert" - {
    "filters names with less than four characters and converts them to uppercase" in {
      val inputNames = List("Ravi", "Akiko", "Satoshi", "Priya", "Juan", "Bola")
      val expectedOutput = List("RAVI", "JUAN", "BOLA")
      val result = filterAndConvert(inputNames)
      result shouldBe expectedOutput
    }

    "returns an empty list when there are no names with less than four characters" in {
      val inputNames = List("Michael", "Sophia", "William")
      val result = filterAndConvert(inputNames)
      result shouldBe empty
    }
  }
}
