import sbt._
import sbt.Keys._

object SlickGenerationSbtPluginBuild extends Build {
  lazy val generatorPath = settingKey[String]("Path to the object containing a main method and generating the slick classes")
  lazy val gentables = taskKey[Unit]("Generate slick files from tables.")

  lazy val gentablesTask = (runner in Compile, dependencyClasspath in Compile, streams) map { (runner, classpath, streams) =>
    toError(runner.run(generatorPath.value, classpath.files, Nil, streams.log))
  }

  lazy val settingsSlick = Seq(
    generatorPath := "",
    gentables <<= gentablesTask
  )
}