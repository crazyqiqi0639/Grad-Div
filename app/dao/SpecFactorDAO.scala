package dao

import java.util.Date
import javax.inject.{ Inject, Singleton }

import model.SpecFactor
import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import slick.jdbc.JdbcProfile
import scala.concurrent.{ ExecutionContext, Future }

@Singleton
class SpecFactorDAO @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._

  private val SpecFactors = TableQuery[SpecFactorTable]

  def all(): Future[Seq[SpecFactor]] = db.run(SpecFactors.result)

  def insert(specFactor: SpecFactor): Future[Unit] =
    db.run(SpecFactors += specFactor).map(_ => ())

  def delete(Name: String): Future[Unit] =
    db.run(SpecFactors.filter(_.name === Name).delete).map(_ => ())

  def update(Name: String, specFactor: SpecFactor): Future[Unit] = {
    val ieltsReportToUpdate: SpecFactor = specFactor.copy(Name)
    db.run(SpecFactors.filter(_.name === Name).update(ieltsReportToUpdate)).map(_ => ())
  }

  def findByName(Name: String): Future[Option[SpecFactor]] =
    db.run(SpecFactors.filter(_.name === Name).result.headOption)

  class SpecFactorTable(tag: Tag) extends Table[SpecFactor](tag, "specfactor") {

    def name = column[String]("name")
    def factor = column[Int]("factor")

    def * = (name, factor) <> (SpecFactor.tupled, SpecFactor.unapply)
  }
}