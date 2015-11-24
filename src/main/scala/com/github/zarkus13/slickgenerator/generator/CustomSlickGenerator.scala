package com.github.zarkus13.slickgenerator.generator

import com.github.zarkus13.slickgenerator.generator.SlickGenerator._

/**
  * Created by alexis on 24/11/15.
  */
abstract class CustomSlickGenerator {

  val url: String
  val user: String
  val password: String
  val driver: Driver
  val genFolder: String
  val genPackage: String
  val excludeTables: List[String] = List("play_evolutions")
  val fileCode: String => String = code => code
  val tableName: Option[String => String] = None // If None : calls super.tableName
  val entityName: Option[String => String] = None // If None : calls super.entityName
  val tableCode: (Seq[String], String) => Seq[String] = (code, entityName) => code
  val tableQueryName: String => String = pluralize
  val columnName: Option[String => String] = None // If None : calls super.rawName
  val columnType: Option[String => Option[String]] = None // If None : calls super.rawType
  val columnEnabled: String => Boolean = name => true

  def generateTables() = {
    SlickGenerator.generateTablesClasses(
      url,
      user,
      password,
      driver,
      genFolder,
      genPackage,
      excludeTables,
      fileCode,
      tableName,
      entityName,
      tableCode,
      tableQueryName,
      columnName,
      columnType,
      columnEnabled
    )
  }

}
