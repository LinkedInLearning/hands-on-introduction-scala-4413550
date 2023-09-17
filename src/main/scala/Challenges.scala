object Challenges {

  /** Write unit tests using ScalaTest to ensure the correctness of this
    * function.
    */
  def calculateSum(numbers: List[Int]): Int = numbers.sum

  /** Implement the method, filterAndConvert, which takes a list of names,
    * filters out the names that have a length greater than 4 characters and
    * converts the remaining names to uppercase.
    */
  def filterAndConvert(names: List[String]): List[String] = names
    .filter(name => name.length <= 4)
    .map(name => name.toUpperCase)

  /** Implement applyFunction, which takes a function and a value, and applies
    * that function to the value. Utilise generic types so this method works
    * with all input types.
    */
  def applyFunction[A, B](f: A => B, value: A): B = ???

  /** You are working on a payment processing system. Implement processPayment,
    * which takes two arguments; amount and cardBalance, both of type Double.
    * The method outputs an `Either`. If the card balance is sufficient to cover
    * the payment, return a Right with the remaining balance after deduction. If
    * the balance is insufficient, return a Left with an error message
    * indicating the insufficient balance.
    */

  def processPayment(
      amount: Double,
      cardBalance: Double
  ): Either[String, Double] = ???

  /** You are developing a simple weather application. As part of this
   * application, you want to model different weather conditions using an
   * enum called WeatherCondition. Define an enum WeatherCondition with cases
   * for various weather conditions. These should be "Sunny", "Cloudy",
   * "Rainy", and "Snowy".
   *
   * Next, write a method getWeatherDescription that takes a WeatherCondition
   * enum case and returns a description of the weather condition. For
   * example, if the input is Sunny, the method should return "It's a sunny
   * day." If it's Cloudy, it should return "It's a cloudy day" and so on.
   */
  enum WeatherCondition:
    case Something

  def getWeatherDescription(condition: WeatherCondition): String = ???

  /** You are developing a notification system. The Notification trait is a
   * template for various notification types. The trait includes a priority, an
   * abstract method for sending the notification and an implemented method to
   * format the message.
   *
   * Your task is to complete the two case classes, EmailNotification and
   * SMSNotification, so that they implement the Notification trait.
   *
   * Set the Priority field. Complete the sendNotification method. For
   * EmailNotification this can be: "Sending email to $emailAddress with
   * message: $message" and for SMS it can be: "Sending SMS to $phoneNumber:
   * $message".
   */
  enum Priority:
    case High, Medium, Low

  sealed trait Notification {
    val priority: Priority

    def sendNotification(message: String): String

    def formatMessage(message: String): String = s"Message: $message"
  }

  final case class EmailNotification(emailAddress: String)

  final case class SMSNotification(phoneNumber: String)
}
