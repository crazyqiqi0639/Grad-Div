package model

import com.google.common.math.DoubleMath

import java.util.Date

case class StudentInfo(
                      applicationNum: Int,
                      studentName: String,
                      studentGender: String,
                      nameOfCompany1: Option[String] = None,
                      designation1: Option[String] = None,
                      dateEmployedFrom1: Option[Date] = None,
                      dateEmployedTo1: Option[Date] = None,
                      duration1: Option[String] = None,
                      nameOfCompany2: Option[String] = None,
                      designation2: Option[String] = None,
                      dateEmployedFrom2: Option[Date] = None,
                      dateEmployedTo2: Option[Date] = None,
                      duration2: Option[String] = None,
                      nameOfCompany3: Option[String] = None,
                      designation3: Option[String] = None,
                      dateEmployedFrom3: Option[Date] = None,
                      dateEmployedTo3: Option[Date] = None,
                      duration3: Option[String] = None,
                      nameOfCompany4: Option[String] = None,
                      designation4: Option[String] = None,
                      dateEmployedFrom4: Option[Date] = None,
//                      nameOfUniv1: String,
//                      locationOfUniv1: String,
//                      qualificationName1: String,
//                      specialization1: String,
//                      classOfHonor1: String,
//                      courseEndDate1: Option[Date] = None,
//                      expectCompletionDate1: Option[Date] = None,
//                      bestPossibleScore1: Double,
//                      gpa1: Double,
//                      rank1: Option[String] = None,
//                      subsidy1: Option[String] = None,
//                      nameOfUniv2: String,
//                      locationOfUniv2: String,
//                      qualificationName2: String,
//                      specialization2: String,
//                      classOfHonor2: String,
//                      courseEndDate2: Option[Date] = None,
//                      expectCompletionDate2: Option[Date] = None,
//                      bestPossibleScore2: Double,
//                      gpa2: Double,
//                      rank2: Option[String] = None,
//                      subsidy2: Option[String] = None,
//                      nameOfUniv3: String,
//                      locationOfUniv3: String,
//                      qualificationName3: String,
//                      specialization3: String,
//                      classOfHonor3: String,
//                      courseEndDate3: Option[Date] = None,
//                      expectCompletionDate3: Option[Date] = None,
//                      bestPossibleScore3: Double,
//                      gpa3: Double,
//                      rank3: Option[String] = None,
//                      subsidy3: Option[String] = None,
//                      ieltsTestDate: Option[Date] = None,
//                      ieltsOverall: Option[Double] = None,
//                      ieltsListening: Option[Double] = None,
//                      ieltsReading: Option[Double] = None,
//                      ieltsWriting: Option[Double] = None,
//                      ieltsSpeaking: Option[Double] = None,
//                      toeflTestDate: Option[Date] = None,
//                      toeflCbtEssay: Option[Int] = None,
//                      toeflCbtListening: Option[Int] = None,
//                      toeflCbtReading: Option[Int] = None,
//                      toeflCbtWriting: Option[Int] = None,
//                      toeflCbtTotal: Option[Int] = None,
//                      toeflPbtWriting: Option[Int] = None,
//                      toeflPbtReading: Option[Int] = None,
//                      toeflPbtListening: Option[Int] = None,
//                      toeflPbtTotal: Option[Int] = None,
//                      toeflIbtReading: Option[Int] = None,
//                      toeflIbtListening: Option[Int] = None,
//                      toeflIbtSpeaking: Option[Int] = None,
//                      toeflIbtWriting: Option[Int] = None,
//                      toeflIbtTotal: Option[Int] = None
                      )

case class Cat(name: String, color: String)

case class Student(ApplicationNum: Long, Name: String, Gender:String )

case class Ielts(ApplicationNum: Long, date: Date, Overall: Double, Listening: Double, Reading: Double, Writing: Double, Speaking: Double)

case class Toefl(
                  ApplicationNum: Long,
                  toeflTestDate: Date,
                  toeflCbtEssay: Option[Int] = None,
                  toeflCbtListening: Option[Int] = None,
                  toeflCbtReading: Option[Int] = None,
                  toeflCbtWriting: Option[Int] = None,
                  toeflCbtTotal: Option[Int] = None,
                  toeflPbtWriting: Option[Int] = None,
                  toeflPbtReading: Option[Int] = None,
                  toeflPbtListening: Option[Int] = None,
                  toeflPbtTotal: Option[Int] = None,
                  toeflIbtReading: Option[Int] = None,
                  toeflIbtListening: Option[Int] = None,
                  toeflIbtSpeaking: Option[Int] = None,
                  toeflIbtWriting: Option[Int] = None,
                  toeflIbtTotal: Option[Int] = None
                )

case class WorkExp(
                  ApplicationNum: Long,
                  Name: String,
                  Designation: String,
                  Date_From: Date,
                  Date_To: Date,
                  Duration: String
                  )

case class StudyExp(
                   ApplicationNum: Long,
                   Name: String,
                   Location: Option[String],
                   Qualification: Option[String],
                   Specialisation: Option[String],
                   ClassOfHonor: Option[String],
                   EndDate: Option[Date],
                   ExpectCompleteDate: Option[Date],
                   BestScore: Option[Double],
                   Gpa: Option[Double],
                   Rank: Option[String],
                   Subsidy: Option[String]
                   )