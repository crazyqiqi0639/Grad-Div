package service

import java.sql.DriverManager
import java.time.Duration
import java.util.Properties
import scala.jdk.CollectionConverters._

object DB extends App {
  var dbConn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "abc123")

  def init(): Unit = {
    createStudentInfoTable()
  }

  def createUserTable(): Unit = {
    dbConn.prepareStatement(s"Drop TABLE IF EXISTS users").executeUpdate()
    dbConn.prepareStatement(s"CREATE TABLE users(" +
      s"Application_Number Int PRIMARY KEY NOT NULL, " +
      s"Name Char(50), " +
      s"Gender Char(50)" +
      s")").executeUpdate()
  }

  def createStudentInfoTable(): Unit = {
    dbConn.prepareStatement(s"Drop TABLE IF EXISTS studentInfo").executeUpdate()
    dbConn.prepareStatement(s"CREATE TABLE studentInfo(" +
      s"Application_Number Int PRIMARY KEY NOT NULL, " +
      s"Name char(50), " +
      s"Gender char(50)," +
      s"Name_Of_Company_1 char(50)," +
      s"Designation_1 char(50)," +
      s"Date_Employed_From_1 char(50), " +
      s"Date_Employed_To_1 char(50), " +
      s"Duration_1 char(50), " +
      s"Name_Of_Company_2 char(50)," +
      s"Designation_2 char(50)," +
      s"Date_Employed_From_2 char(50), " +
      s"Date_Employed_To_2 char(50), " +
      s"Duration_2 char(50), " +
      s"Name_Of_Company_3 char(50)," +
      s"Designation_3 char(50)," +
      s"Date_Employed_From_3 char(50), " +
      s"Date_Employed_To_3 char(50), " +
      s"Duration_3 char(50), " +
      s"Name_Of_Company_4 char(50)," +
      s"Designation_4 char(50)," +
      s"Date_Employed_From_4 char(50), " +
      s"Date_Employed_To_4 char(50), " +
      s"Duration_4 char(50), " +
      s"Name_Of_university_1 char(50), " +
      s"Location_1 char(50), " +
      s"Qualification_1 char(50), " +
      s"Specialisation_1 char(50), " +
      s"Class_Of_Honor_1 char(50), " +
      s"Course_End_Date_1 char(50), " +
      s"Expect_End_Date_1 char(50), " +
      s"Best_Score_1 Float, " +
      s"GPA_1 Float, " +
      s"Rank_1 char(50), " +
      s"Subsidy_1 char(50), " +
      s"Name_Of_university_2 char(50), " +
      s"Location_2 char(50), " +
      s"Qualification_2 char(50), " +
      s"Specialisation_2 char(50), " +
      s"Class_Of_Honor_2 char(50), " +
      s"Course_End_Date_2 char(50), " +
      s"Expect_End_Date_2 char(50), " +
      s"Best_Score_2 Float, " +
      s"GPA_2 Float, " +
      s"Rank_2 char(50), " +
      s"Subsidy_2 char(50), " +
      s"Name_Of_university_3 char(50), " +
      s"Location_3 char(50), " +
      s"Qualification_3 char(50), " +
      s"Specialisation_3 char(50), " +
      s"Class_Of_Honor_3 char(50), " +
      s"Course_End_Date_3 char(50), " +
      s"Expect_End_Date_3 char(50), " +
      s"Best_Score_3 Float, " +
      s"GPA_3 Float, " +
      s"Rank_3 char(50), " +
      s"Subsidy_3 char(50), " +
      s"IELTS_TEST_DATE char(50), " +
      s"IELTS_OVERALL float, " +
      s"IELTS_Listening float, " +
      s"IELTS_Reading float, " +
      s"IELTS_Writing float, " +
      s"IELTS_Speaking float," +
      s"TOEFL_TestDate char(50), " +
      s"TOEFL_CBT_Essay Int, " +
      s"TOEFL_CBT_Listening Int, " +
      s"TOEFL_CBT_Reading Int, " +
      s"TOEFL_CBT_Writing Int, " +
      s"TOEFL_CBT_Total Int, " +
      s"TOEFL_PBT_Writing Int, " +
      s"TOEFL_PBT_Reading Int, " +
      s"TOEFL_PBT_Listening Int, " +
      s"TOEFL_PBT_Total Int, " +
      s"TOEFL_IBT_Reading Int, " +
      s"TOEFL_IBT_Listening Int, " +
      s"TOEFL_IBT_Speaking Int, " +
      s"TOEFl_IBT_Writing Int, " +
      s"TOEFL_IBT_Total Int"+
      s")").executeUpdate()
  }

  init()

}

