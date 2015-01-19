
organization := "com.kollecti"

name := "slick-generation-sbt-plugin"

version := "0.1.3"

sbtPlugin := true

libraryDependencies ++= Seq(
  "com.kollecti" %% "slick-generator" % "0.1.3"
)

publishTo := Some("Sonatype OSS Releases" at "https://oss.sonatype.org/content/repositories/releases")