package service

import java.sql.DriverManager
import java.time.Duration
import java.util.Properties
import scala.jdk.CollectionConverters._

object DB extends App{
  val dbConn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "abc123")

  def init(): Unit = {
    createUserTable()
    createIeltsTable()
    createToeflCbtTable()
    createToeflIbtTable()
    createToeflPbtTable()
    createWorkExpTable()
    createStudyExpTable()
  }

  def createUserTable(): Unit = {
    dbConn.prepareStatement(s"Drop TABLE IF EXISTS users").executeUpdate()
    dbConn.prepareStatement(s"CREATE TABLE users(" +
      s"Application_Number Int PRIMARY KEY NOT NULL, " +
      s"Name Char(50), " +
      s"Gender Char(50)" +
      s")").executeUpdate()
  }

  def createIeltsTable(): Unit = {
    dbConn.prepareStatement(s"Drop TABLE IF EXISTS ielts").executeUpdate()
    dbConn.prepareStatement(s"CREATE TABLE ielts(" +
      s"Application_Number Int PRIMARY KEY NOT NULL, " +
      s"Date Char(50), " +
      s"Overall float, " +
      s"Listening float, " +
      s"Reading float, " +
      s"Writing float, " +
      s"Speaking float" +
      s")").executeUpdate()
  }

  def createToeflCbtTable(): Unit = {
    dbConn.prepareStatement(s"Drop TABLE IF EXISTS toefl_cbt").executeUpdate()
    dbConn.prepareStatement(s"CREATE TABLE toefl_cbt(" +
      s"Application_Number Int PRIMARY KEY NOT NULL, " +
      s"Date Char(50), " +
      s"Overall float, " +
      s"Listening float, " +
      s"Reading float, " +
      s"Writing float, " +
      s"Speaking float" +
      s")").executeUpdate()
  }

  def createToeflPbtTable(): Unit = {
    dbConn.prepareStatement(s"Drop TABLE IF EXISTS toefl_pbt").executeUpdate()
    dbConn.prepareStatement(s"CREATE TABLE toefl_pbt(" +
      s"Application_Number Int PRIMARY KEY NOT NULL, " +
      s"Date Char(50), " +
      s"Overall float, " +
      s"Listening float, " +
      s"Reading float, " +
      s"Writing float, " +
      s"Speaking float" +
      s")").executeUpdate()
  }

  def createToeflIbtTable(): Unit = {
    dbConn.prepareStatement(s"Drop TABLE IF EXISTS toefl_ibt").executeUpdate()
    dbConn.prepareStatement(s"CREATE TABLE toefl_ibt(" +
      s"Application_Number Int PRIMARY KEY NOT NULL, " +
      s"Date Char(50), " +
      s"Overall float, " +
      s"Listening float, " +
      s"Reading float, " +
      s"Writing float, " +
      s"Speaking float" +
      s")").executeUpdate()
  }

  def createWorkExpTable(): Unit = {
    dbConn.prepareStatement(s"Drop TABLE IF EXISTS work_exp").executeUpdate()
    dbConn.prepareStatement(s"CREATE TABLE work_exp(" +
      s"Application_Number Int PRIMARY KEY NOT NULL, " +
      s"Name char(50), " +
      s"Designation char(50), " +
      s"Date_From char(50), " +
      s"Date_to char(50), " +
      s"Duration char(50)" +
      s")").executeUpdate()
  }

  def createStudyExpTable(): Unit = {
    dbConn.prepareStatement(s"Drop TABLE IF EXISTS study_exp").executeUpdate()
    dbConn.prepareStatement(s"CREATE TABLE study_exp(" +
      s"Application_Number Int PRIMARY KEY NOT NULL, " +
      s"Name char(50), " +
      s"Location char(50), " +
      s"Qualification char(50), " +
      s"Specialisation char(50), " +
      s"Class_Of_Honor char(50), " +
      s"End_Date char(50), " +
      s"Expect_Complete_Date char(50), " +
      s"Best_Score float, " +
      s"GPA float, " +
      s"Rank char(50), " +
      s"Subsidy char(5)" +
      s")").executeUpdate()
  }

  init()


}
