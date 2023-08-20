import java.io.{File, FileWriter}
import scala.io.Source
import scala.util.{Using, Try}
import Etl.*
import Etl.EtlError.*

object FileUtils:
  def extract(input: String): Either[EtlError, List[String]] =
    try Right(Using.resource(Source.fromFile(input))(_.getLines.toList))
    catch case e: Exception => Left(ExtractError)

  def load[A](data: List[A], output: String): Either[EtlError, Unit] =
    val maybeFileWriter: Either[EtlError, FileWriter] =
      try Right(new FileWriter(new File(output)))
      catch case _: Exception => Left(LoadError)

    for
      fileWriter <- maybeFileWriter
      _ <- writeFile(fileWriter, data)
    yield ()

  private def writeFile[A](fileWriter: FileWriter, data: List[A]) =
    val write =
      Try(fileWriter.write(data.mkString("\n"))).toEither.left.map(_ =>
        LoadError
      )
    fileWriter.close()
    write
  end writeFile
