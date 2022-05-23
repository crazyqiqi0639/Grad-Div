ThisBuild / scalaVersion := "2.13.8"

ThisBuild / version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """Grad-Div""",
    libraryDependencies ++= Seq(
      guice,
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test,
      "org.postgresql" % "postgresql" % "42.3.2",
      "org.apache.poi" % "poi" % "4.1.2",
      "org.apache.poi" % "poi-ooxml" % "4.1.2"
    )
  )