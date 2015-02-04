package com.github.zarkus13.slickgenerator.play

import java.io.File

import com.github.zarkus13.slickgenerator.generator.{Driver, SlickGenerator}
import com.github.zarkus13.slickgenerator.generator.SlickGenerator._
import play.api.Application
import play.api.db.evolutions._
import play.api.mvc.{RequestHeader, Result}
import play.core.BuildLink

/**
 * Created by Alexis on 13/03/14.
 */
abstract class SlickPlayEvolutionPlugin(app: Application) extends EvolutionsPlugin(app) {

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

  override def handleWebCommand(request: RequestHeader, sbtLink: BuildLink, path: File): Option[Result] = {
    val result = super.handleWebCommand(request, sbtLink, path)

    generateTables()

    result
  }

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
