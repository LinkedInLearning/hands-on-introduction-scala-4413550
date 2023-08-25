import io.circe._, io.circe.generic.semiauto._

case class User(id: Int, name: String, age: Int)

implicit val userDecoder: Decoder[User] = deriveDecoder