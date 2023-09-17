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
}
