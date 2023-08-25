import Etl.*
import org.scalatest.BeforeAndAfterAll
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers
import pureconfig.*
import pureconfig.generic.derivation.EnumConfigReader
import pureconfig.generic.derivation.default.*

import java.io.{File, FileWriter}
import java.nio.file.{Files, Path}
import scala.io.Source
import scala.util.{Success, Try, Using}

class EtlSpec extends AnyFreeSpec with Matchers with BeforeAndAfterAll {

  val stringInput = "src/test/resources/testInput.txt"
  val intInput = "src/test/resources/testInput2.txt"
  val jsonInput = "src/test/resources/testInput3.json"

  val stringOutput = "src/test/resources/testOutput.txt"
  val intOutput = "src/test/resources/testOutput2.txt"
  val jsonOutput = "src/test/resources/testOutput3.json"

  val stringConfig = "string-test"
  val intConfig = "int-test"
  val jsonConfig = "json-test"

  override def beforeAll() =
    writeTestInputFile(stringInput, "HELLO WORLD")
    writeTestInputFile(intInput, "0\n1\n2\n3\n4\n5")
    val inputJson: String =
      """
        |[
        |{"id": 1, "name": "amina", "age": 35},
        |{"id": 2, "name": "marylene", "age": 14}
        |]
        |""".stripMargin
    writeTestInputFile(jsonInput, inputJson)

  override def afterAll() =
    List(
      stringInput,
      intInput,
      jsonInput,
      stringOutput,
      intOutput,
      jsonOutput,
    ).foreach(path => Files.delete(Path.of(path)))

  "etl" - {

    "transforms a text file by making all the text lowercase and saves it to a new file" in {
      val expected = List("hello world")
      runIntegratedTest(stringConfig, Etl.StringImpl, expected)
    }
    "transforms a text file by doubling all integers and saves this to a new file" in {
      val expected = List("0", "2", "4", "6", "8", "10")
      runIntegratedTest(intConfig, Etl.IntImpl, expected)
    }
    "transforms a json file by removing all invalid users" in {
      val expected = List("User(1,amina,35)")
      runIntegratedTest(jsonConfig, Etl.JsonImpl, expected)
    }

    "outputs an extract error if the input file path does not exist" in {
      val configWithErroneousInputFilePath =
        """
          |input-file-path = ""
          |output-file-path = "src/test/resources/testOutput.txt"
          |etl-impl = string-impl
          |""".stripMargin

      isExpectedError(
        configWithErroneousInputFilePath,
        Etl.StringImpl,
        EtlError.ExtractError
      )
    }
    "outputs a load error if the output file path does not exist" in {
      val configWithErroneousOutputFilePath =
        s"""
             |input-file-path = $intInput
             |output-file-path = ""
             |etl-impl = int-impl
             |""".stripMargin

      isExpectedError(
        configWithErroneousOutputFilePath,
        Etl.IntImpl,
        EtlError.LoadError
      )
    }
  }

  private def writeTestInputFile(path: String, contents: String) =
    val fileWriter = new FileWriter(new File(path))
    try {
      fileWriter.write(contents)
    } finally {
      fileWriter.close()
    }

  private def runIntegratedTest[A, B, C](
      configPath: String,
      etlImpl: Etl[A, B],
      expected: C
  ) =
    ConfigSource.default.at(configPath).load[EtlConfig] match
      case Left(_) => fail("there's been a problem loading the test config")
      case Right(config) =>
        etl(config, etlImpl)
        Using(Source.fromFile(config.outputFilePath))(
          _.getLines.toList
        ) shouldEqual Success(expected)
  end runIntegratedTest

  private def isExpectedError[A, B](
      config: String,
      impl: Etl[A, B],
      error: EtlError
  ) =
    ConfigSource
      .string(config)
      .load[EtlConfig] match
      case Left(_) => fail("there's been a problem loading the test config")
      case Right(config) =>
        etl(config, impl) shouldEqual Left(error)
}
