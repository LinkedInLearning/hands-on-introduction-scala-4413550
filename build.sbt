lazy val root = (project in file("."))
  .settings(
    name := "hands-on-scala",
    scalaVersion := "3.3.0",
    libraryDependencies ++= Seq(
      "com.github.pureconfig" %% "pureconfig-core" % "0.17.3",
      "org.scalatest" %% "scalatest" % "3.2.15" % "test"
    )
  )
