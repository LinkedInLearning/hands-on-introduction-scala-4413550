import java.io.{File, FileWriter}
import scala.io.Source
import scala.util.{Using, Try}

@main def run: Unit =
  val input: String = "src/main/resources/input.txt"
  val output: String = "src/main/resources/output.txt"
  etl(input, output)

def etl(inputFilePath: String, outputFilePath: String): Unit =
  val extracted = extract(inputFilePath)
  val transformed = transform(extracted)
  load(transformed, outputFilePath)
end etl

def extract(input: String): List[String] =
  Using.resource(Source.fromFile(input))(_.getLines.toList)
end extract

def transform(data: List[String]): List[String] =
  data.map(_.toLowerCase)
end transform

def load(data: List[String], output: String): Unit =
  val file = new File(output)
  val fileWriter = new FileWriter(file)
  fileWriter.write(data.mkString("\n"))
  fileWriter.close()
end load
