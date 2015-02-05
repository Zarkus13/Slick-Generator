package com.github.zarkus13.slickgenerator.sbt

import com.etsy.sbt.CompileQuick._
import sbt._
import sbt.Keys._

/**
 * Created by Alexis on 22/03/2014.
 */
object SlickGenerationSbtPlugin extends Plugin {

  lazy val generatorPath = settingKey[String]("Path to the object containing a main method and generating the slick classes")
  lazy val gentables = taskKey[Unit]("Generate slick files from tables.")

  lazy val gentablesTask = (
    runner in Compile,
    compilers in Compile,
    scalaSource in Compile,
    classDirectory in Compile,
    scalacOptions in Compile,
    compileInputs in (Compile, compile),
    generatorPath,
    dependencyClasspath in Compile,
    streams
    ) map {
    (runner, compilers, scalaSource, classDirectory, scalacOptions, compileInputs, path, classpath, streams) => {
      val classDirFile = classDirectory.getAbsoluteFile
      if(!classDirFile.exists() || !classDirFile.isDirectory) {
        classDirFile.delete()
        classDirFile.mkdir()
      }
      
      compilers.scalac(
        Seq(file(scalaSource.absolutePath + "/" + path.replaceAll("\\.", "/") + ".scala")),
        noChanges,
        classpath.files,
        classDirectory,
        scalacOptions,
        noopCallback,
        1000,
        compileInputs.incSetup.cache,
        streams.log
      )
      
      val realClasspath = classpath.files :+ classDirectory.getAbsoluteFile
      toError(runner.run(path, realClasspath, Nil, streams.log))
    }
  }

  lazy val settingsSlick = Seq(
    generatorPath := "",
    gentables <<= gentablesTask
  )

}
