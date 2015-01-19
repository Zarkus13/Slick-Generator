
organization := "com.kollecti"

name := "play-evolution-slick-plugin"

scalaVersion := "2.11.4"

version := "0.1.3"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play" % "2.3.7",
  "com.typesafe.play" %% "play-jdbc" % "2.3.7",
  "com.kollecti" %% "slick-generator" % "0.1.3"
)

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "Sonatype OSS Releases" at "https://oss.sonatype.org/content/repositories/releases"

publishTo := Some("Sonatype OSS Releases" at "https://oss.sonatype.org/content/repositories/releases")