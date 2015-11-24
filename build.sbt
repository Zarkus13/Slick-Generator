import SonatypeKeys._

sonatypeSettings

useGpg := true

organization := "com.github.zarkus13"

name := "slick-generator"

scalaVersion := "2.10.6"

version := "0.2.4.4"

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick-codegen" % "2.1.0",
  "mysql" % "mysql-connector-java" % "5.1.21"
)

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "Sonatype OSS Releases" at "https://oss.sonatype.org/content/repositories/releases"

profileName := "com.github.zarkus13"

pomExtra := {
  <url>https://github.com/Zarkus13/Slick-Generator</url>
    <licenses>
      <license>
        <name>Apache 2</name>
        <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>
      </license>
    </licenses>
    <scm>
      <connection>scm:git:github.com/Zarkus13/Slick-Generator</connection>
      <developerConnection>scm:git:git@github.com:Zarkus13/Slick-Generator</developerConnection>
      <url>github.com/Zarkus13/Slick-Generator</url>
    </scm>
    <developers>
      <developer>
        <id>Zarkus13</id>
        <name>Alexis Weil</name>
        <url>http://alexis-weil.com</url>
      </developer>
    </developers>
}
