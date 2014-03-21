import sbt._
import Keys._

object SlickGeneratorBuild extends Build {

  lazy val generatorProj = Project(
    id = "slick-generator",
    base = file(".")
  )

  lazy val sbtPluginProj = Project(
    id = "slick-generation-sbt-plugin",
    base = file("slick-generation-sbt-plugin")
  ) dependsOn generatorProj

  lazy val playEvolPluginProj = Project(
    id = "play-evolution-slick-plugin",
    base = file("play-evolution-slick-plugin")
  ) dependsOn generatorProj

}