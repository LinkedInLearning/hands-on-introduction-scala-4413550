import java.io.{File, FileWriter}
import scala.io.Source
import scala.util.Using

object FileUtils:
  def extract(input: String): List[String] =
    Using.resource(Source.fromFile(input))(_.getLines.toList)
  end extract

  def load[A](data: List[A], output: String): Unit =
    val file = new File(output)
    val fileWriter = new FileWriter(file)
    fileWriter.write(data.mkString("\n"))
    fileWriter.close()
  end load
end FileUtils
