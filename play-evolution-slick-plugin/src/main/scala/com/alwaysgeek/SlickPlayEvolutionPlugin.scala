package com.alwaysgeek

import _root_.utils.SlickGenerator
import play.api.Application
import play.api.db.evolutions._
import com.alwaysgeek.SlickGenerator._
import play.api.mvc.{SimpleResult, RequestHeader}
import play.core.SBTLink
import java.io.File

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
  val tableName: String => String = name => name
  val entityName: String => String = name => name
  val tableCode: (Seq[String], String) => Seq[String] = (code, entityName) => code,
  val columnName: String => String = name => unCapitalize(camelCase("(?i)^*(fk)$".r.replaceFirstIn(name, "Id")))
  val columnType: String => Option[String] = col => Some(col)
  val columnEnabled: String => Boolean = name => true

  override def handleWebCommand(request: RequestHeader, sbtLink: SBTLink, path: File): Option[SimpleResult] = {
    val result = super.handleWebCommand(request, sbtLink, path)

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
      columnName,
      columnType,
      columnEnabled
    )

    result
  }
}

trait SlickPlayEvolutionMain {
  val generator: SlickPlayEvolutionPlugin
}
