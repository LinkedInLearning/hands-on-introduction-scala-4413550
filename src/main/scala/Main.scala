import java.io.{File, FileWriter}
import scala.io.Source
import scala.util.{Using, Try}
import Etl.*
import FileUtils.*

@main def run: Unit =
  val input: String = "src/main/resources/input.txt"
  val output: String = "src/main/resources/output.txt"

  import Etl.IntImpl
  etl(input, output) match
    case Left(error) => println(s"Failure: $error")
    case Right(_)    => println(s"Success!")
