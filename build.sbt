
organization := "com.alwaysgeek"

name := "slick-generator"

version := "0.1.1"

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "2.0.2",
  "mysql" % "mysql-connector-java" % "5.1.21"
)

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "Sonatype OSS Releases" at "https://oss.sonatype.org/content/repositories/releases"

