import SonatypeKeys._

sonatypeSettings

useGpg := true

organization := "com.github.zarkus13"

name := "play-evolution-slick-plugin"

scalaVersion := "2.11.4"

version := "0.2.0"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play" % "2.3.7",
  "com.typesafe.play" %% "play-jdbc" % "2.3.7",
  "com.github.zarkus13" %% "slick-generator" % "0.2.0"
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