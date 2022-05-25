package model

import slick.jdbc.PostgresProfile.api._

object gradDivDB {

  final case class studentInfo(
                              applicationNum : Int,
                              name: String,
                              gender: String
                              )
  class studentInfoTable(tag: Tag) extends Table[studentInfo](tag, "coffeestest") {
    def applicationNum = column[Int]("ApplicationNum")
    def name  = column[String]("Name")
    def gender = column[String]("Gender")
    def * = (applicationNum,name, gender) <> (studentInfo.tupled, studentInfo.unapply)
  }
}
