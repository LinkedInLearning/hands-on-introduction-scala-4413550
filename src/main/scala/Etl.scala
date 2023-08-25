import io.circe.parser.*

object Etl:
  enum EtlError:
    case ExtractError, LoadError, JsonParseError

  sealed trait Etl[A, B]:
    def extract(input: String): Either[EtlError, A]
    def transform(data: A): Either[EtlError, B]
    def load(data: B, output: String): Either[EtlError, Unit]

  given StringImpl: Etl[List[String], List[String]] with
    def extract(input: String): Either[EtlError, List[String]] =
      FileUtils.extract(input)
    def transform(data: List[String]): Either[EtlError, List[String]] = Right(
      data.map(_.toLowerCase)
    ).withLeft[EtlError]
    def load(data: List[String], output: String): Either[EtlError, Unit] =
      FileUtils.load(data, output)

  given IntImpl: Etl[List[String], List[Int]] with
    def extract(input: String): Either[EtlError, List[String]] =
      FileUtils.extract(input)
    def transform(data: List[String]): Either[EtlError, List[Int]] = Right(
      data.map(_.toInt).map(_ * 2)
    ).withLeft[EtlError]
    def load(data: List[Int], output: String): Either[EtlError, Unit] =
      FileUtils.load(data, output)

  given JsonImpl: Etl[List[String], List[User]] with
    def extract(input: String): Either[EtlError, List[String]] =
      FileUtils.extract(input)
    def transform(data: List[String]): Either[EtlError, List[User]] =
      for {
        users <- decode[List[User]](data.mkString).left.map(_ => EtlError.JsonParseError)
        validatedUsers = users.filter(user => user.age >= 18)
      } yield validatedUsers
    def load(data: List[User], output: String): Either[EtlError, Unit] =
      FileUtils.load(data, output)

  def etl[A, B](config: EtlConfig, etl: Etl[A, B]): Either[EtlError, Unit] =
    for
      extracted <- etl.extract(config.inputFilePath)
      transformed <- etl.transform(extracted)
      _ <- etl.load(transformed, config.outputFilePath)
    yield ()
