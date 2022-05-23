package service

import org.apache.poi.xssf.usermodel.XSSFWorkbook

import java.io.{File, FileInputStream}


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
      for(i <- 0 until xssfRow.getPhysicalNumberOfCells){
        //获取表格每一行的每一列
        val title = titleRow.getCell(i).toString
        val value = xssfRow.getCell(i).toString
        print(title + ":" +value+" " )
      }
      println
    }
  }

}
