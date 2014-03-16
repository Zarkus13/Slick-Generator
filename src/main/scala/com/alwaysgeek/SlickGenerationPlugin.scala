package com.alwaysgeek

import sbt._
import sbt.Keys._

/**
 * Created by Alexis on 13/02/14.
 */
object SlickGenerationPlugin extends Plugin {

  lazy val url = settingKey[String]("Database's URL.")

  lazy val user = settingKey[String]("Database's user.")

  lazy val password = settingKey[String]("Database's password.")

  lazy val driver = settingKey[Driver]("Driver to use for the connection to the database.")

  lazy val genFolder = settingKey[String]("Folder in which the files will be generated.")

  lazy val genPackage = settingKey[String]("Scala package for the generated files.")

  lazy val excludeTables = settingKey[List[String]]("Tables to exclude when generating slick classes.")

  lazy val fileCode = settingKey[String => String]("Transformation to apply on the code of each generated file.")

  lazy val tableName = settingKey[String => String]("Transformation to apply on the name of each generated table.")

  lazy val entityName = settingKey[String => String]("Transformation to apply on the name of each entity generated entity's.")

  lazy val tableCode = settingKey[(Seq[String], String) => Seq[String]]("Transformation to apply on the code of each generated table.")

  lazy val columnName = settingKey[String => String]("Transformation to apply on the name of each generated column.")

  lazy val columnType = settingKey[String => Option[String]]("Function that defines the scala type for a column according to its type in the database. Some[String] can be returned, containing the scala type for this column. If None is returned, the default type from slick's code generator is used.")

  lazy val columnEnabled = settingKey[String => Boolean]("Defines if the scala field for this column must be generated, according to its name")

  lazy val gentables = taskKey[Unit]("Generate slick files from tables.")

  val settingsSlick = Seq(
    url := "",
    user := "",
    password := "",
    driver := MySQL(),
    genFolder := sourceManaged.value.getName,
    genPackage := "slick.tables",
    excludeTables := List(),
    fileCode := ((code: String) => code),
    tableName := ((name: String) => name),
    entityName := ((name: String) => name),
    tableCode := ((code: Seq[String], name: String) => code),
    columnName := ((name: String) => name),
    columnType := ((col: String) => Some(col)),
    columnEnabled := ((name: String) => true),
    gentables := {
      SlickGenerator.generateTablesClasses(
        url = url.value,
        user = user.value,
        password = password.value,
        driver = driver.value,
        genFolder = genFolder.value,
        genPackage = genPackage.value,
        excludeTables = excludeTables.value,
        fileCode = fileCode.value,
        tableName = tableName.value,
        entityName = entityName.value,
        tableCode = tableCode.value,
        columnName = columnName.value,
        columnType = columnType.value,
        columnEnabled = columnEnabled.value
      )
    }
  )

  implicit class StringWrapper(str: String) {
    def camelCase = SlickGenerator.camelCase(str)

    def pluralize = SlickGenerator.pluralize(str)

    def unCapitalize = SlickGenerator.unCapitalize(str)
  }

}
