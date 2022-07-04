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

  def insert(studyExp: StudyExp) = db.run(StudyExps += studyExp).map{_ => ()}

  def delete(AppNum: Long): Future[Unit] =
    db.run(StudyExps.filter(_.applicationNum === AppNum).delete).map(_ => ())

  def update(AppNum: Long, studyExp: StudyExp): Future[Unit] = {
    val studyExpToUpdate: StudyExp = studyExp.copy(AppNum)
    db.run(StudyExps.filter(_.applicationNum === AppNum).update(studyExpToUpdate).map(_ => ()))
  }

  def findAllByAppNum(AppNum: Long): Future[Seq[StudyExp]] =
    db.run(StudyExps.filter(_.applicationNum === AppNum).result)

  def findByAppNum(AppNum: Long): Future[Option[StudyExp]] =
    db.run(StudyExps.filter(_.applicationNum === AppNum).result.headOption)

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

}
