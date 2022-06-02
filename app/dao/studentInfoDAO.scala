package dao

import java.util.Date
import javax.inject.{ Inject, Singleton }

import model.StudentInfo
import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import slick.jdbc.JdbcProfile

import scala.concurrent.{ ExecutionContext, Future }

@Singleton()
class studentInfoDAO @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext)
 extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  private val Students = TableQuery[studentInfoTable]

  def all(): Future[Seq[StudentInfo]] = db.run(Students.result)

  def insert(student: StudentInfo): Future[Unit] = db.run(Students += student).map { _ => () }

  class studentInfoTable(tag: Tag) extends Table[StudentInfo](tag, "studentInfo") {
    implicit val dateColumnType = MappedColumnType.base[Date, Long](d => d.getTime, d => new Date(d))

    def applicationNum = column[Int]("Application_Number", O.PrimaryKey)
    def studentName = column[String]("NAME")
    def studentGender = column[String]("Gender")
    def nameOfCompany1 = column[Option[String]]("Name_Of_Company_1")
    def designation1 = column[Option[String]]("Designation_1")
    def dateEmployedFrom1 = column[Option[Date]]("Date_Employed_From_1")
    def dateEmployedTo1 = column[Option[Date]]("Date_Employed_To_1")
    def duration1 = column[Option[String]]("Duration_1")
    def nameOfCompany2 = column[Option[String]]("Name_Of_Company_2")
    def designation2 = column[Option[String]]("Designation_2")
    def dateEmployedFrom2 = column[Option[Date]]("Date_Employed_From_2")
    def dateEmployedTo2 = column[Option[Date]]("Date_Employed_To_2")
    def duration2 = column[Option[String]]("Duration_2")
    def nameOfCompany3 = column[Option[String]]("Name_Of_Company_3")
    def designation3 = column[Option[String]]("Designation_3")
    def dateEmployedFrom3 = column[Option[Date]]("Date_Employed_From_3")
    def dateEmployedTo3 = column[Option[Date]]("Date_Employed_To_3")
    def duration3 = column[Option[String]]("Duration_3")
    def nameOfCompany4 = column[Option[String]]("Name_Of_Company_4")
    def designation4 = column[Option[String]]("Designation_4")
    def dateEmployedFrom4 = column[Option[Date]]("Date_Employed_From_4")
    def dateEmployedTo4 = column[Option[Date]]("Date_Employed_To_4")
    def duration4 = column[Option[String]]("Duration_4")
    def nameOfUniv1 = column[Option[String]]("Name_Of_university_1")
//    def locationOfUniv1 = column[Option[String]]("Location_1")
//    def qualificationName1 = column[Option[String]]("Qualification_1")
//    def specialization1 = column[Option[String]]("Specialisation_1")
//    def classOfHonor1 = column[Option[String]]("Class_Of_Honor_1")
//    def courseEndDate1 = column[Option[Date]]("Course_End_Date_1")
//    def expectCompletionDate1 = column[Option[Date]]("Expect_End_Date_1")
//    def bestPossibleScore1 = column[Option[Double]]("Best_Score_1")
//    def gpa1 = column[Option[Double]]("GPA_1 Float")
//    def rank1 = column[Option[String]]("Rank_1")
//    def subsidy1 = column[Option[String]]("Subsidy_1")
//    def nameOfUniv2 = column[Option[String]]("Name_Of_university_2")
//    def locationOfUniv2 = column[Option[String]]("Location_2")
//    def qualificationName2 = column[Option[String]]("Qualification_2")
//    def specialization2 = column[Option[String]]("Specialisation_2")
//    def classOfHonor2 = column[Option[String]]("Class_Of_Honor_2")
//    def courseEndDate2 = column[Option[Date]]("Course_End_Date_2")
//    def expectCompletionDate2 = column[Option[Date]]("Expect_End_Date_2")
//    def bestPossibleScore2 = column[Option[Double]]("Best_Score_2")
//    def gpa2 = column[Option[Double]]("GPA_2")
//    def rank2 = column[Option[String]]("Rank_2")
//    def subsidy2 = column[Option[String]]("Subsidy_2")
//    def nameOfUniv3 = column[Option[String]]("Name_Of_university_3")
//    def locationOfUniv3 = column[Option[String]]("Location_3")
//    def qualificationName3 = column[Option[String]]("Qualification_3")
//    def specialization3 = column[Option[String]]("Specialisation_3")
//    def classOfHonor3 = column[Option[String]]("Class_Of_Honor_3")
//    def courseEndDate3 = column[Option[Date]]("Course_End_Date_3")
//    def expectCompletionDate3 = column[Option[Date]]("Expect_End_Date_3")
//    def bestPossibleScore3 = column[Option[Double]]("Best_Score_3")
//    def gpa3 = column[Option[Double]]("GPA_3")
//    def rank3 = column[Option[String]]("Rank_3")
//    def subsidy3 = column[Option[String]]("Subsidy_3")
//    def ieltsTestDate = column[Option[Date]]("IELTS_TEST_DATE")
//    def ieltsOverall = column[Option[Double]]("IELTS_OVERALL")
//    def ieltsListening = column[Option[Double]]("IELTS_Listening")
//    def ieltsReading = column[Option[Double]]("IELTS_Reading")
//    def ieltsWriting = column[Option[Double]]("IELTS_Writing")
//    def ieltsSpeaking = column[Option[Double]]("IELTS_Speaking")
//    def toeflTestDate = column[Option[Date]]("TOEFL_TestDate")
//    def toeflCbtEssay = column[Option[Int]]("TOEFL_CBT_Essay")
//    def toeflCbtListening = column[Option[Int]]("TOEFL_CBT_Listening")
//    def toeflCbtReading = column[Option[Int]]("TOEFL_CBT_Reading")
//    def toeflCbtWriting = column[Option[Int]]("TOEFL_CBT_Writing")
//    def toeflCbtTotal = column[Option[Int]]("TOEFL_CBT_Total")
//    def toeflPbtWriting = column[Option[Int]]("TOEFL_PBT_Writing")
//    def toeflPbtReading = column[Option[Int]]("TOEFL_PBT_Reading")
//    def toeflPbtListening = column[Option[Int]]("TOEFL_PBT_Listening")
//    def toeflPbtTotal = column[Option[Int]]("TOEFL_PBT_Total")
//    def toeflIbtReading = column[Option[Int]]("TOEFL_IBT_Reading")
//    def toeflIbtListening = column[Option[Int]]("TOEFL_IBT_Listening")
//    def toeflIbtSpeaking = column[Option[Int]]("TOEFL_IBT_Speaking")
//    def toeflIbtWriting = column[Option[Int]]("TOEFl_IBT_Writing")
//    def toeflIbtTotal = column[Option[Int]]("TOEFL_IBT_Total")

//    def * = (applicationNum, studentName, studentGender,
//      nameOfCompany1, designation1, dateEmployedFrom1, dateEmployedTo1, duration1,
//      nameOfCompany2, designation2, dateEmployedFrom2, dateEmployedTo2, duration2,
//      nameOfCompany3, designation3, dateEmployedFrom3, dateEmployedTo3, duration3,
//      nameOfCompany4, designation4, dateEmployedFrom4, dateEmployedTo4, duration4,
//      nameOfUniv1, locationOfUniv1, qualificationName1, specialization1, classOfHonor1, courseEndDate1, expectCompletionDate1, bestPossibleScore1, gpa1, rank1, subsidy1,
//      nameOfUniv2, locationOfUniv2, qualificationName2, specialization2, classOfHonor2, courseEndDate2, expectCompletionDate2, bestPossibleScore2, gpa2, rank2, subsidy2,
//      nameOfUniv3, locationOfUniv3, qualificationName3, specialization3, classOfHonor3, courseEndDate3, expectCompletionDate3, bestPossibleScore3, gpa3, rank3, subsidy3,
//      ieltsTestDate, ieltsOverall, ieltsListening, ieltsReading, ieltsWriting, ieltsSpeaking,
//      toeflTestDate,
//      toeflCbtEssay, toeflCbtListening, toeflCbtReading, toeflCbtWriting, toeflCbtTotal,
//      toeflPbtListening, toeflPbtReading, toeflPbtWriting, toeflPbtTotal,
//      toeflIbtListening, toeflIbtReading, toeflIbtWriting, toeflIbtSpeaking, toeflIbtTotal
//    )
    def * = (applicationNum, studentName, studentGender,
  nameOfCompany1, designation1, dateEmployedFrom1, dateEmployedTo1, duration1,
  nameOfCompany2, designation2, dateEmployedFrom2, dateEmployedTo2, duration2,
  nameOfCompany3, designation3, dateEmployedFrom3, dateEmployedTo3, duration3,
  nameOfCompany4, designation4, dateEmployedFrom4
) <> (StudentInfo.tupled, StudentInfo.unapply)
  }
}
