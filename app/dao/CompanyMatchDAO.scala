package dao

import javax.inject.{ Inject, Singleton }

import model.CompanyMatch
import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import slick.jdbc.JdbcProfile
import scala.concurrent.{ ExecutionContext, Future }

@Singleton
class CompanyMatchDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._

  private val companyMatches = TableQuery[CompanyMatchTable]

  def all(): Future[Seq[CompanyMatch]] = db.run(companyMatches.result)

  def insert(companyMatch: CompanyMatch): Future[Unit] =
    db.run(companyMatches += companyMatch).map(_ => ())

  def delete(Name: String): Future[Unit] =
    db.run(companyMatches.filter(_.shortName === Name).delete).map(_ => ())

  def update(Name: String, companyMatch: CompanyMatch): Future[Unit] = {
    val companyMatchToUpdate: CompanyMatch = companyMatch.copy(Name)
    db.run(companyMatches.filter(_.shortName === Name).update(companyMatchToUpdate)).map(_ => ())
  }

  def findByName(Name: String): Future[Option[CompanyMatch]] =
    db.run(companyMatches.filter(_.shortName === Name).result.headOption)

  class CompanyMatchTable(tag: Tag) extends Table[CompanyMatch](tag, "companymatch") {

    def shortName = column[String]("shortname")
    def originalName = column[String]("name")

    def * = (shortName, originalName) <> (CompanyMatch.tupled, CompanyMatch.unapply)
  }
}