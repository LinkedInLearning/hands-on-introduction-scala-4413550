val circeVersion = "0.14.5"

lazy val root = (project in file("."))
  .settings(
    name := "hands-on-scala",
    scalaVersion := "3.3.0",
    libraryDependencies ++= Seq(
      "com.github.pureconfig" %% "pureconfig-core" % "0.17.3",
      "io.circe" %% "circe-core" % circeVersion,
      "io.circe" %% "circe-generic" % circeVersion,
      "io.circe" %% "circe-parser" % circeVersion,
      "org.scalatest" %% "scalatest" % "3.2.15" % "test"
    )
  )
