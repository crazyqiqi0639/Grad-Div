package model

case class Student(ApplicationNum: Long, Name: String, Gender:String )

case class Ielts(ApplicationNum: Long, date: Option[Long], Overall: Option[Double], Listening: Option[Double], Reading: Option[Double], Writing: Option[Double], Speaking: Option[Double])

case class Toefl(
                  ApplicationNum: Long,
                  toeflTestDate: Option[Long],
                  toeflCbtEssay: Option[Int],
                  toeflCbtListening: Option[Int],
                  toeflCbtReading: Option[Int],
                  toeflCbtWriting: Option[Int],
                  toeflCbtTotal: Option[Int],
                  toeflPbtWriting: Option[Int],
                  toeflPbtReading: Option[Int],
                  toeflPbtListening: Option[Int],
                  toeflPbtTotal: Option[Int],
                  toeflIbtReading: Option[Int],
                  toeflIbtListening: Option[Int],
                  toeflIbtSpeaking: Option[Int],
                  toeflIbtWriting: Option[Int],
                  toeflIbtTotal: Option[Int]
                )

case class WorkExp(
                  ApplicationNum: Long,
                  Name: Option[String],
                  Designation: Option[String],
                  Date_From: Option[Long],
                  Date_To: Option[Long],
                  Duration: Option[String]
                  )

case class StudyExp(
                   ApplicationNum: Long,
                   Name: Option[String],
                   Location: Option[String],
                   Qualification: Option[String],
                   Specialisation: Option[String],
                   ClassOfHonor: Option[String],
                   EndDate: Option[Long],
                   ExpectCompleteDate: Option[Long],
                   BestScore: Option[Double],
                   Gpa: Option[Double],
                   Rank: Option[String],
                   Subsidy: Option[String],
                   NameOfCollege: Option[String],
                   QualificationType: Option[String]
                   )

case class University(
                     Name: String,
                     Country: String,
                     Other: String,
                     Scheme: Int,
                     Rank: Int
                     )

case class Company(
                  Name: String,
                  Rank: Int
                  )

case class SpecFactor(
                     Name: String,
                     Factor: Double
                     )

case class UnivMatch(
                      ShortName: String,
                      OriginalName:String
                    )

case class CompanyMatch(
                       ShortName:String,
                       OriginalName:String
                       )

case class searchDemo(
                     Name: String
                     )