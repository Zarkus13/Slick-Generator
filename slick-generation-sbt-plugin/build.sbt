import SonatypeKeys._

sonatypeSettings

useGpg := true

organization := "com.github.zarkus13"

name := "slick-generation-sbt-plugin"

scalaVersion := "2.10.4"

version := "0.1.1"

sbtPlugin := true

libraryDependencies ++= Seq(
  "com.github.zarkus13" %% "slick-generator" % "0.1.1"
)

addSbtPlugin("com.etsy" % "sbt-compile-quick-plugin" % "0.5.0")

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