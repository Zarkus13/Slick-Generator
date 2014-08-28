
organization := "com.alwaysgeek"

name := "play-evolution-slick-plugin"

version := "0.1.2.1"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play" % "2.3.3",
  "com.typesafe.play" %% "play-jdbc" % "2.3.3",
  "com.alwaysgeek" %% "slick-generator" % "0.1.2.1"
)

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "Sonatype OSS Releases" at "https://oss.sonatype.org/content/repositories/releases"