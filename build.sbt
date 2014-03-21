
name := "slick-generator"

organization := "com.alwaysgeek"

version := "0.1.0"

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "2.0.0",
  "mysql" % "mysql-connector-java" % "5.1.21"
)

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "Sonatype OSS Releases" at "https://oss.sonatype.org/content/repositories/releases"

