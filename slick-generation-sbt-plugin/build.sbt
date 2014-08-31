
organization := "com.alwaysgeek"

name := "slick-generation-sbt-plugin"

version := "0.1.2.3"

sbtPlugin := true

libraryDependencies ++= Seq(
  "com.alwaysgeek" %% "slick-generator" % "0.1.2.1"
)

publishTo := Some("Sonatype OSS Releases" at "https://oss.sonatype.org/content/repositories/releases")