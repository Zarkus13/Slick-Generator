package com.alwaysgeek

import sbt._
import sbt.Keys._

/**
 * Created by Alexis on 22/03/2014.
 */
object SlickGenerationSbtPlugin extends Plugin {

  lazy val generatorPath = settingKey[String]("Path to the object containing a main method and generating the slick classes")
  lazy val gentables = taskKey[Unit]("Generate slick files from tables.")

  lazy val gentablesTask = (runner in Compile, generatorPath, dependencyClasspath in Compile, streams) map { (runner, path, classpath, streams) =>
    println("\n\nClasspath files :")
    classpath.files.foreach(f => println(f.getPath))
    println()

    toError(runner.run(path, classpath.files, Nil, streams.log))
  }

  lazy val settingsSlick = Seq(
    generatorPath := "",
    gentables <<= gentablesTask
  )

}
