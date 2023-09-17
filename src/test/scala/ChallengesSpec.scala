import org.scalatest.*
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers
import Challenges.*

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

  "applyFunction" - {
    "applies a function to an integer" in {
      val square: Int => Int = num => num * num
      val result = applyFunction(square, 5)
      result shouldBe 25
    }

    "applies a function to a string" in {
      val greet: String => String = name => s"Hello, $name!"
      val result = applyFunction(greet, "Akin")
      result shouldBe "Hello, Akin!"
    }

    "applies a function to a case class" in {
      case class Person(name: String, age: Int)
      val getDescription: Person => String =
        person => s"${person.name} is ${person.age} years old."
      val person = Person("Coco", 65)
      val result = applyFunction(getDescription, person)
      result shouldBe "Coco is 65 years old."
    }
  }

  "processPayment" - {
    "processes successful payment" in {
      val amount = 50.0
      val cardBalance = 100.0
      val result = processPayment(amount, cardBalance)
      result shouldBe Right(50.0)
    }

    "handles insufficient balance" in {
      val amount = 150.0
      val cardBalance = 100.0
      val result = processPayment(amount, cardBalance)
      result shouldBe Left("Insufficient balance")
    }
  }
  "getWeatherDescription" - {
    "should describe the weather conditions correctly" - {
      "for Sunny weather" in {
        val description = getWeatherDescription(WeatherCondition.Sunny)
        description shouldBe "It's a sunny day."
      }

      "for Cloudy weather" in {
        val description = getWeatherDescription(WeatherCondition.Cloudy)
        description shouldBe "It's a cloudy day."
      }

      "for Rainy weather" in {
        val description = getWeatherDescription(WeatherCondition.Rainy)
        description shouldBe "It's a rainy day."
      }

      "for Snowy weather" in {
        val description = getWeatherDescription(WeatherCondition.Snowy)
        description shouldBe "It's a snowy day."
      }
    }
  }

  "Notifications" - {
    "EmailNotification" in {
      val emailAddress = "user@example.com"
      val emailNotification = EmailNotification(emailAddress, Priority.High)
      val message = "Hello, this is an email"
      val formattedMessage = emailNotification.formatMessage(message)
      val result = emailNotification.sendNotification(message)

      formattedMessage shouldBe s"Message: $message"
      result shouldBe s"Sending email to $emailAddress with message: $message"
    }
    "SMSNotification" in {
      val phoneNumber = "07525015566"
      val smsNotification = SMSNotification(phoneNumber, Priority.Medium)
      val message = "Hello, this is an SMS"
      val formattedMessage = smsNotification.formatMessage(message)
      val result = smsNotification.sendNotification(message)

      formattedMessage shouldBe s"Message: $message"
      result shouldBe s"Sending SMS to $phoneNumber: $message"
    }
  }
}
