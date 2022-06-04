package service

import model.Student
import org.apache.poi.xssf.usermodel.XSSFWorkbook

import java.io.{File, FileInputStream}
import scala.util.control.Breaks.break


object readExcel extends App {
  val filePath = new File("Resource/GDA-Data-Sample.xlsx")
  val fs = new FileInputStream(filePath)
  val xssfWorkbook: XSSFWorkbook = new XSSFWorkbook(fs)
  for(i <- 0 until xssfWorkbook.getNumberOfSheets){
    //获取表格每一个sheet
    val xssfSheet = xssfWorkbook.getSheetAt(i)
    val titleRow = xssfSheet.getRow(0)
    for(row <- 1 to xssfSheet.getLastRowNum()){
      //获取表格每一行
      val xssfRow = xssfSheet.getRow(row)
      val AppNum = xssfRow.getCell(0).getNumericCellValue.toInt
      val StuName = xssfRow.getCell(1).toString
      val StuGender = xssfRow.getCell(2).toString
      val student = Student(AppNum, StuName, StuGender)
      val nameOfCompany1 = xssfRow.getCell(3).toString
      val designation1 = xssfRow.getCell(4).toString
      val dateEmployed1 = xssfRow.getCell(5).getRawValue.toLong
      for(i <- 0 until xssfRow.getPhysicalNumberOfCells){
        //获取表格每一行的每一列
        val title = titleRow.getCell(i).toString
        if (i.equals(5)) {
          println(title + ": " +xssfRow.getCell(i).getRawValue.toLong+" " )
          break
        }
      }
      println
    }
  }

}
