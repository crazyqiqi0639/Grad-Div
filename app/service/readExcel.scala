package service

import model.{Student, StudyExp, WorkExp}
import org.apache.poi.xssf.usermodel.XSSFWorkbook

import java.io.{File, FileInputStream}
import java.util.Date
import scala.util.control.Breaks.break
import java.text.SimpleDateFormat


object readExcel extends App {
  def tranTimeToLong(tm:String) :Long={
    val fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val dt = fm.parse(tm)
    val aa = fm.format(dt)
    val tim: Long = dt.getTime()
    tim
  }

  val filePath = new File("Resource/GDA-Data-Sample.xlsx")
  val fs = new FileInputStream(filePath)
  val xssfWorkbook: XSSFWorkbook = new XSSFWorkbook(fs)

  val sdf = new SimpleDateFormat("yyyy/MM/dd")
  for(i <- 0 until xssfWorkbook.getNumberOfSheets){
    //获取表格每一个sheet
    val xssfSheet = xssfWorkbook.getSheetAt(i)
    val titleRow = xssfSheet.getRow(0)
    for(row <- 1 to xssfSheet.getLastRowNum()) {
      //获取表格每一行
      val xssfRow = xssfSheet.getRow(row)

      val AppNum = xssfRow.getCell(0).getNumericCellValue.toInt

//      val NameOfCompany_1: Option[String] = Option {
//        val name = xssfRow.getCell(3).getRawValue
//        if (name != null) {
//          xssfRow.getCell(3).toString
//        } else {
//          "Not Provided"
//        }
//      }
//      println("Name of Company: " + NameOfCompany_1.get + " " + row)
//
//      val Designation_1: Option[String] = Option {
//        val designation = xssfRow.getCell(4).getRawValue
//        if (designation != null) {
//          xssfRow.getCell(4).toString
//        } else {
//          "Not Provided"
//        }
//      }
//      println(titleRow.getCell(4) + ": " + Designation_1.get + " " + row)
//
//      val DateEmployedFrom_1: Option[Long] = Option {
//        var raw = xssfRow.getCell(5).getRawValue
//        if (raw != null) {
//          val value = raw.toDouble
//          val date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value)
//          val time = sdf.format(date)
//          sdf.parse(time).getTime
//        } else {
//          0
//        }
//      }
//      println(titleRow.getCell(5) + ": " + DateEmployedFrom_1.get + " " + row)
//
//      val DateEmployedTo_1: Option[Long] = Option {
//        val raw = xssfRow.getCell(6).getRawValue
//        if (raw != null) {
//          val value = raw.toDouble
//          val date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value)
//          val time = sdf.format(date)
//          sdf.parse(time).getTime
//        } else {
//          0
//        }
//      }
//      println(titleRow.getCell(6) + ": " + DateEmployedTo_1.get + " " + row)
//
//      val Duration_1: Option[String] = Option {
//        val duration = xssfRow.getCell(7).getRawValue
//        if (duration != null) {
//          xssfRow.getCell(7).toString
//        } else {
//          "Not Provided"
//        }
//      }
////      println(titleRow.getCell(7) + ": " + Duration_1.get + " " + row)
//      val workExperience_1 = WorkExp(ApplicationNum = AppNum, Name = NameOfCompany_1, Designation = Designation_1, Date_From = DateEmployedFrom_1,
//        Date_To = DateEmployedTo_1, Duration = Duration_1)
//
//      if (!Designation_1.get.equals("Not Provided")) {
//        println("Not Jump this one!")
//      }

      val NameOfUniversity_1: Option[String] = Option {
        val duration = xssfRow.getCell(23).getRawValue
        if (duration != null) {
          xssfRow.getCell(23).toString
        } else {
          "Not Provided"
        }
      }
      println(titleRow.getCell(23) + ": " + NameOfUniversity_1.get + " " + row)

      val Location_1: Option[String] = Option {
        val duration = xssfRow.getCell(24).getRawValue
        if (duration != null) {
          xssfRow.getCell(24).toString
        } else {
          "Not Provided"
        }
      }
      println(titleRow.getCell(24) + ": " + Location_1.get + " " + row)

      val Qualification_1: Option[String] = Option {
        val duration = xssfRow.getCell(25).getRawValue
        if (duration != null) {
          xssfRow.getCell(25).toString
        } else {
          "Not Provided"
        }
      }
      println(titleRow.getCell(25) + ": " + Qualification_1.get + " " + row)

      val Specialisation_1: Option[String] = Option {
        val duration = xssfRow.getCell(26).getRawValue
        if (duration != null) {
          xssfRow.getCell(26).toString
        } else {
          "Not Provided"
        }
      }
      println(titleRow.getCell(26) + ": " + Specialisation_1.get + " " + row)

      val ClassOfHonor_1: Option[String] = Option {
        val duration = xssfRow.getCell(27).getRawValue
        if (duration != null) {
          xssfRow.getCell(27).toString
        } else {
          "Not Provided"
        }
      }
      println(titleRow.getCell(27) + ": " + ClassOfHonor_1.get + " " + row)

      val CourseEndDate_1: Option[Long] = Option {
        val raw = xssfRow.getCell(28).getRawValue
        if (raw != null) {
          val value = raw.toDouble
          val date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value)
          val time = sdf.format(date)
          sdf.parse(time).getTime
        } else {
          0
        }
      }
      println(titleRow.getCell(28) + ": " + CourseEndDate_1.get + " " + row)

      val ExpectEndDate_1: Option[Long] = Option {
        val raw = xssfRow.getCell(29).getRawValue
        if (raw != null) {
          val value = raw.toDouble
          val date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value)
          val time = sdf.format(date)
          sdf.parse(time).getTime
        } else {
          0
        }
      }
      println(titleRow.getCell(29) + ": " + ExpectEndDate_1.get + " " + row)

      val BestScore_1: Option[Double] = Option {
        val raw = xssfRow.getCell(30).getRawValue
        if (raw != null) {
          raw.toDouble
        } else {
          0.0
        }
      }
      println(titleRow.getCell(30) + ": " + BestScore_1.get + " " + row)

      val GPA_1: Option[Double] = Option {
        val raw = xssfRow.getCell(31).getRawValue
        if (raw != null) {
          raw.toDouble
        } else {
          0.0
        }
      }
      println(titleRow.getCell(31) + ": " + GPA_1.get + " " + row)

      val Rank_1: Option[String] = Option {
        val duration = xssfRow.getCell(32).getRawValue
        if (duration != null) {
          xssfRow.getCell(32).toString
        } else {
          "Not Provided"
        }
      }
      println(titleRow.getCell(32) + ": " + Rank_1.get + " " + row)

      val Subsidy_1: Option[String] = Option {
        val duration = xssfRow.getCell(33).getRawValue
        if (duration != null) {
          xssfRow.getCell(33).toString
        } else {
          "Not Provided"
        }
      }
      println(titleRow.getCell(33) + ": " + Subsidy_1.get + " " + row)

      val NameofCollege_1: Option[String] = Option {
        val duration = xssfRow.getCell(34).getRawValue
        if (duration != null) {
          xssfRow.getCell(34).toString
        } else {
          "Not Provided"
        }
      }
      println(titleRow.getCell(34) + ": " + NameofCollege_1.get + " " + row)

      val QualificationType_1: Option[String] = Option {
        val duration = xssfRow.getCell(35).getRawValue
        if (duration != null) {
          xssfRow.getCell(35).toString
        } else {
          "Not Provided"
        }
      }
      println(titleRow.getCell(35) + ": " + QualificationType_1.get + " " + row)

      val studyExperience_1 = StudyExp(
        ApplicationNum = AppNum,
        Name = NameOfUniversity_1,
        Location = Location_1,
        Qualification = Qualification_1,
        Specialisation = Specialisation_1,
        ClassOfHonor = ClassOfHonor_1,
        EndDate = CourseEndDate_1,
        ExpectCompleteDate = ExpectEndDate_1,
        BestScore = BestScore_1,
        Gpa = GPA_1,
        Rank = Rank_1,
        Subsidy = Subsidy_1,
        NameOfCollege = NameofCollege_1,
        QualificationType = QualificationType_1
      )
      println
    }}

}
