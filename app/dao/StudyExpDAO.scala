package dao

import java.util.Date
import javax.inject.{ Inject, Singleton }

import model.StudyExp
import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import slick.jdbc.JdbcProfile
import scala.concurrent.{ ExecutionContext, Future }

class StudyExpDAO @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile]{
  import profile.api._

  def StudyExps = TableQuery[StudyExpTable]

  def all(): Future[Seq[StudyExp]] = db.run(StudyExps.result)

  class StudyExpTable (tag: Tag) extends Table[StudyExp](tag, "study") {
    implicit val dateColumnType = MappedColumnType.base[Date, Long](d => d.getTime, d => new Date(d))

    def applicationNum = column[Long]("application_number")
    def Name = column[String]("name")
    def Location = column[Option[String]]("location")
    def Qualification = column[Option[String]]("qualification")
    def Specialisation = column[Option[String]]("specification")
    def ClassOfHonor = column[Option[String]]("class_of_honor")
    def EndDate = column[Option[Date]]("end_date")
    def ExpectCompleteDate = column[Option[Date]]("expect_complete_date")
    def BestScore = column[Option[Double]]("best_score")
    def Gpa = column[Option[Double]]("gpa")
    def Rank = column[Option[String]]("rank")
    def Subsidy = column[Option[String]]("subsidy")

    def * = (
      applicationNum, Name, Location,
      Qualification, Specialisation, ClassOfHonor,
      EndDate, ExpectCompleteDate, BestScore, Gpa, Rank, Subsidy
    )<>(StudyExp.tupled, StudyExp.unapply)
  }

}