package model

import java.sql.DriverManager
import java.time.Duration
import java.util.Properties
import scala.jdk.CollectionConverters._

object testDB extends App{
  var dbConn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "abc123")

  def init(): Unit = {
    createUserTable()
    createIeltsTable()
    createToeflTable()
    createWorkExpTable()
    createStudyExpTable()
  }

  def createUserTable(): Unit = {
    dbConn.prepareStatement(s"Drop TABLE IF EXISTS student").executeUpdate()
    dbConn.prepareStatement(s"CREATE TABLE student(" +
      s"application_number Int PRIMARY KEY NOT NULL, " +
      s"name Char(50), " +
      s"gender Char(50)" +
      s")").executeUpdate()
  }

  def insertIntoUser(Application : Int, Name: String, Gender: String): Unit = {
    dbConn.prepareStatement("INSERT INTO student (application_number, name, gender) " +
      s"VALUES ($Application, '$Name', '$Gender');").executeUpdate()
  }

  def createIeltsTable(): Unit = {
    dbConn.prepareStatement(s"Drop TABLE IF EXISTS ielts").executeUpdate()
    dbConn.prepareStatement(s"CREATE TABLE ielts(" +
      s"application_number Int PRIMARY KEY NOT NULL, " +
      s"date Char(50), " +
      s"overall float, " +
      s"listening float, " +
      s"reading float, " +
      s"writing float, " +
      s"speaking float" +
      s")").executeUpdate()
  }

  def createToeflTable(): Unit = {
    dbConn.prepareStatement(s"Drop TABLE IF EXISTS toefl").executeUpdate()
    dbConn.prepareStatement(s"CREATE TABLE toefl(" +
      s"application_number Int PRIMARY KEY NOT NULL," +
      s"date char(50)," +
      s"cbt_essay Int," +
      s"cbt_listening Int," +
      s"cbt_reading Int," +
      s"cbt_writing Int, " +
      s"cbt_total Int, " +
      s"pbt_writing Int," +
      s"pbt_reading Int," +
      s"pbt_listening Int," +
      s"pbt_total Int," +
      s"ibt_reading Int, " +
      s"ibt_listening Int," +
      s"ibt_speaking Int," +
      s"ibt_writing Int," +
      s"ibt_total Int" +
      s")").executeUpdate()
  }


  def createWorkExpTable(): Unit = {
    dbConn.prepareStatement(s"Drop TABLE IF EXISTS work").executeUpdate()
    dbConn.prepareStatement(s"CREATE TABLE work(" +
      s"application_number Int PRIMARY KEY NOT NULL, " +
      s"name char(50), " +
      s"designation char(50), " +
      s"date_from char(50), " +
      s"date_to char(50), " +
      s"duration char(50)" +
      s")").executeUpdate()
  }

  def createStudyExpTable(): Unit = {
    dbConn.prepareStatement(s"Drop TABLE IF EXISTS study").executeUpdate()
    dbConn.prepareStatement(s"CREATE TABLE study(" +
      s"application_number Int PRIMARY KEY NOT NULL, " +
      s"name char(50), " +
      s"location char(50), " +
      s"qualification char(50), " +
      s"specialisation char(50), " +
      s"class_of_honor char(50), " +
      s"end_date char(50), " +
      s"expect_complete_date char(50), " +
      s"best_score float, " +
      s"gpa float, " +
      s"rank char(50), " +
      s"subsidy char(5)" +
      s")").executeUpdate()
  }

  init()
}
