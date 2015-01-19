organization := "com.kollecti"

name := "slick-generator"

scalaVersion := "2.11.4"

version := "0.1.3"

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick-codegen" % "2.1.0",
  "mysql" % "mysql-connector-java" % "5.1.21"
)

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "Sonatype OSS Releases" at "https://oss.sonatype.org/content/repositories/releases"