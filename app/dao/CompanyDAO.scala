package dao

import model.Company
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}
import javax.inject.{Inject, Singleton}

@Singleton()
class CompanyDAO @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile]{
  import profile.api._

  private val Companies = TableQuery[CompanyTable]

  def all(): Future[Seq[Company]] = db.run(Companies.result)

  def insert(company: Company): Future[Unit] =
    db.run(Companies += company).map(_ => ())

  def delete(name: String): Future[Unit] =
    db.run(Companies.filter(_.Name === name).delete).map(_ => ())

  def update(name: String, company: Company): Future[Unit] = {
    val companyToUpdate: Company = company.copy(name)
    db.run(Companies.filter(_.Name === name).update(companyToUpdate)).map(_ => ())
  }

  class CompanyTable(tag: Tag) extends Table[Company](tag, "company") {
    def Name = column[String]("name")
    def Rank = column[Int]("rank")

    def * =
      (Name, Rank)<>(Company.tupled, Company.unapply)
  }

}
