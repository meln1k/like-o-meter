name := """like-o-meter"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  "org.scalaj" %% "scalaj-http" % "0.3.16",
  "org.webjars" %% "webjars-play" % "2.3.0",
  "org.webjars" % "bootstrap" % "3.2.0",
  "org.webjars" % "jquery" % "2.1.1",
  jdbc,
  anorm,
  cache,
  ws
)
