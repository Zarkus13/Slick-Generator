package com.kollecti

import scala.slick.driver.{PostgresDriver, JdbcDriver, MySQLDriver}
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.jdbc.meta._
import scala.slick.model.codegen.SourceCodeGenerator

/**
 * Created by Alexis on 24/02/14.
 */

sealed trait Driver {
  val jdbcDriver: String
  val slickDriver: String
  val driverClass: JdbcDriver
}
case class MySQL() extends Driver {
  val jdbcDriver: String = "com.mysql.jdbc.Driver"
  val slickDriver: String = "scala.slick.driver.MySQLDriver"
  val driverClass: JdbcDriver = MySQLDriver
}
case class PostgreSQL() extends Driver {
  val jdbcDriver: String = "org.postgresql.Driver"
  val slickDriver: String = "scala.slick.driver.PostgresDriver"
  val driverClass: JdbcDriver = PostgresDriver
}

object SlickGenerator {

  def generateTablesClasses(
      url: String,
      user: String,
      password: String,
      driver: Driver,
      genFolder: String,
      genPackage: String,
      excludeTables: List[String] = List(),
      fileCode: String => String = code => code,
      tableName: Option[String => String] = None, // If None : calls super.tableName
      entityName: Option[String => String] = None, // If None : calls super.entityName
      tableCode: (Seq[String], String) => Seq[String] = (code, entityName) => code,
      tableQueryName: String => String = pluralize,
      columnName: Option[String => String] = None, // If None : calls super.rawName
      columnType: Option[String => Option[String]] = None, // If None : calls super.rawType
      columnEnabled: String => Boolean = name => true
   ) = {

    def tName = tableName
    def eName = entityName
    
    val db = Database.forURL(
      url = url,
      user = user,
      password = password,
      driver = driver.jdbcDriver
    )

    db.withSession { implicit session =>

      driver.driverClass.getTables
        .list
        .filterNot(t => excludeTables.contains(t.name.name))
        .foreach(t => {
          val model = createModel(
            Seq(t),
            MySQLDriver
          )

          val codeGen = new SourceCodeGenerator(model) {

            override def tableName: (String) => String = tName getOrElse super.tableName

            override def entityName: (String) => String = eName getOrElse super.entityName

            override def code = fileCode(super.code)

            override def Table = new Table(_) {

              override def code = tableCode(super.code, entityName(model.name.table))

              override def TableValue = new TableValue{
                override def rawName = tableQueryName(super.rawName)
              }

              override def Column = new Column(_) {
                override def enabled: Boolean = columnEnabled(model.name)

                override def rawName: String = columnName match {
                  case None => super.rawName
                  case Some(columnName) => columnName(model.name)
                }

                override def rawType = columnType match {
                  case None => super.rawType
                  case Some(columnType) => columnType(model.tpe) getOrElse parseType(model.tpe)
                }
              }
            }
          }

          val taName = (tableName match {
            case None => codeGen.tableName
            case Some(f) => f
          })(t.name.name)

          codeGen.writeToFile(
            driver.slickDriver,
            genFolder,
            genPackage,
            taName,
            taName + ".scala"
          )
        })
    }
  }

  def camelCase(str: String): String = {

    def capitalizeParts(parts: Array[String]): String = {
      parts.map(_.capitalize).mkString("")
    }

    capitalizeParts(
      capitalizeParts(
        capitalizeParts(
          str.toLowerCase.split("_")
        ).split("-")
      ).split(" ")
    )
  }

  def pluralize(str: String) = {
    "^*(s|x|z)$".r findFirstMatchIn str match {
      case Some(_) => str
      case None => str + "s"
    }
  }

  def unCapitalize(str: String) = {
    val firstChar = str.charAt(0).toString
    str.replaceFirst(
      firstChar,
      firstChar.toLowerCase
    )
  }
}
