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

  def insertIntoUser(Application : Int, Name: String, Gender: String): Unit = {
    dbConn.prepareStatement("INSERT INTO users (Application_Number, Name, Gender) " +
      s"VALUES ($Application, '$Name', '$Gender');").executeUpdate()
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

  def insertIntoIelts(Application : Int, Date : String, Overall: Float,
                      Listening: Float, Reading: Float, Writing: Float, Speaking: Float): Unit = {
    dbConn.prepareStatement("INSERT INTO ielts(Application_Number, Date, Overall, " +
      "Listening, Reading, Writing, Speaking) " +
      s"VALUES ($Application, '$Date', $Overall, " +
      s"$Listening, $Reading, $Writing, $Speaking);").executeUpdate()
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

  def insertIntoToeflCbt(Application : Int, Date : String, Overall: Float,
                         Listening: Float, Reading: Float, Writing: Float, Speaking: Float): Unit = {
    dbConn.prepareStatement("INSERT INTO toefl_cbt(Application_Number, Date, Overall, " +
      "Listening, Reading, Writing, Speaking) " +
      s"VALUES ($Application, '$Date', $Overall, " +
      s"$Listening, $Reading, $Writing, $Speaking);").executeUpdate()
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

  def insertIntoToeflPbt(Application : Int, Date : String, Overall: Float,
                         Listening: Float, Reading: Float, Writing: Float, Speaking: Float): Unit = {
    dbConn.prepareStatement("INSERT INTO toefl_pbt(Application_Number, Date, Overall, " +
      "Listening, Reading, Writing, Speaking) " +
      s"VALUES ($Application, '$Date', $Overall, " +
      s"$Listening, $Reading, $Writing, $Speaking);").executeUpdate()
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

  def insertIntoToeflIbt(Application : Int, Date : String, Overall: Float,
                         Listening: Float, Reading: Float, Writing: Float, Speaking: Float): Unit = {
    dbConn.prepareStatement("INSERT INTO toefl_ibt(Application_Number, Date, Overall, " +
      "Listening, Reading, Writing, Speaking) " +
      s"VALUES ($Application, '$Date', $Overall, " +
      s"$Listening, $Reading, $Writing, $Speaking);").executeUpdate()
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

  def insertIntoWorkExp(Application: Int, Name: String, Designation: String,
                        Date_From: String, Date_to: String, duration: String): Unit = {
    dbConn.prepareStatement("INSERT INTO toefl_ibt(Application_Number, Name, Designation, " +
      "Date_From, Date_to, Duration) " +
      s"VALUES ($Application, '$Name', '$Designation', " +
      s"'$Date_From', '$Date_to', '$duration');").executeUpdate()
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

  def insertIntoStudyExp(Application_Number: Int, Name: String, Location: String, Qualification: String,
                         Specialisation: String, Class_Of_Honor: String, End_Date: String, Expect_Complete_Date: String,
                         Best_Score: Float, GPA: Float, Rank: String, Subsidy: String): Unit = {
    dbConn.prepareStatement("INSERT INTO toefl_ibt(Application_Number, Name, Location, " +
      "Qualification, Specialisation, Class_Of_Honor, End_Date, Expect_Complete_Date, " +
      "Best_Score, GPA, Rank, Subsidy) " +
      s"VALUES ($Application_Number, '$Name', '$Location', " +
      s"'$Qualification', '$Specialisation', '$Class_Of_Honor', '$End_Date', '$Expect_Complete_Date', " +
      s"$Best_Score, $GPA, '$Rank', '$Subsidy');").executeUpdate()
  }
  init()
}
