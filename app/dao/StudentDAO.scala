package dao

import java.util.Date
import javax.inject.{Inject, Singleton}
import model.{Student, StudyExp, WorkExp}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton()
class StudentDAO  @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile]{
  import profile.api._

  private val Students = TableQuery[StudentTable]
  private val workExps = TableQuery[WorkExpTable]
  private val StudyExps = TableQuery[StudyExpTable]

  def all(): Future[Seq[Student]] = db.run(Students.result)

  def insert(student: Student) :Future[Unit] = db.run(Students += student).map{ _ => ()}

  def findByName(Name: String): Future[Seq[(Student,StudyExp)]] = {
    val query_1 = {
      Students.joinLeft(StudyExps.filter(_.Name like("%"+Name+"%"))).on(_.applicationNum ===_.applicationNum).result
    }
    val query_2 = {
      Students.joinLeft(StudyExps).on(_.applicationNum ===_.applicationNum).filter(m => (m._1.Name like("%"+Name+"%"))).result
    }
    val query = for {
      (a, b) <- Students join (StudyExps) on(_.applicationNum ===_.applicationNum)
    } yield (a, b)

    val query_3 = {
      query.filter{
        case (a, b) => (a.Name.toLowerCase like("%" + Name.toLowerCase + "%")) || (b.Name.toLowerCase like("%" + Name.toLowerCase + "%")) || (b.Location.toLowerCase like("%" + Name.toLowerCase + "%")) || (b.Specialisation.toLowerCase like("%" + Name.toLowerCase + "%"))
      }
    }
    db.run(query_3.result)
  }

  def findByAppNum(AppNum: Long): Future[Option[Student]] =
    db.run(Students.filter(_.applicationNum === AppNum).result.headOption)

  def list():  Future[Seq[(Student,StudyExp)]] = {
    val query = {
      Students.join(StudyExps).on(_.applicationNum ===_.applicationNum)
    }
    val query_2 = {
      query.join(workExps).on(_._1.applicationNum === _.applicationNum)
    }
    db.run(query.result)
  }

  def delete(AppNum: Long): Future[Unit] =
    db.run(Students.filter(_.applicationNum === AppNum).delete).map(_ => ())

  def update(AppNum: Long, student: Student):Future[Unit]= {
    val studentToUpdate: Student = student.copy(AppNum)
    db.run(Students.filter(_.applicationNum === AppNum).update(studentToUpdate)).map(_ => ())
  }

  class StudentTable(tag: Tag) extends Table[Student](tag, "student") {

    def applicationNum = column[Long]("application_number")
    def Name = column[String]("name")
    def Gender = column[String]("gender")

    def * = (applicationNum, Name, Gender)<>(Student.tupled, Student.unapply)
  }

  class StudyExpTable (tag: Tag) extends Table[StudyExp](tag, "study") {
    implicit val dateColumnType = MappedColumnType.base[Date, Long](d => d.getTime, d => new Date(d))

    def applicationNum = column[Long]("application_number")
    def Name = column[Option[String]]("name")
    def Location = column[Option[String]]("location")
    def Qualification = column[Option[String]]("qualification")
    def Specialisation = column[Option[String]]("specialisation")
    def ClassOfHonor = column[Option[String]]("class_of_honor")
    def EndDate = column[Option[Long]]("end_date")
    def ExpectCompleteDate = column[Option[Long]]("expect_complete_date")
    def BestScore = column[Option[Double]]("best_score")
    def Gpa = column[Option[Double]]("gpa")
    def Rank = column[Option[String]]("rank")
    def Subsidy = column[Option[String]]("subsidy")
    def NameOfCollege = column[Option[String]]("name_of_college")
    def QualicationType = column[Option[String]]("qualification_type")

    def * = (
      applicationNum, Name, Location,
      Qualification, Specialisation, ClassOfHonor,
      EndDate, ExpectCompleteDate, BestScore, Gpa, Rank, Subsidy, NameOfCollege, QualicationType
    )<>(StudyExp.tupled, StudyExp.unapply)
  }

  class WorkExpTable(tag: Tag) extends Table[WorkExp](tag, "work") {
    implicit val dateColumnType = MappedColumnType.base[Date, Long](d => d.getTime, d => new Date(d))

    def applicationNum = column[Long]("application_number")
    def Name = column[Option[String]]("name")
    def Designation = column[Option[String]]("designation")
    def Date_From = column[Option[Long]]("date_from")
    def Date_To = column[Option[Long]]("date_to")
    def Duration = column[Option[String]]("duration")

    def * = (applicationNum, Name, Designation, Date_From, Date_To, Duration)<>(WorkExp.tupled, WorkExp.unapply)

  }

}
