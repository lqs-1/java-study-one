package com.lqs.test;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class POIText {


    /**
     *
     * XSSFWorkbook: 工作簿
     * XSSFSheet: 工作表
     * Row: 行
     * Cell: 单元格
     *
     * @throws IOException
     */

    @Test
    public void test1() throws IOException {
        // 打开工作簿
        XSSFWorkbook excel = new XSSFWorkbook(new FileInputStream("/home/lqs/下载/白银现货统计(1).xlsx"));
        // 选择工作表
        XSSFSheet sheet = excel.getSheetAt(0);
        // 遍历工作表的每一行
        for (Row rol : sheet) {
//            System.out.println(cells);
            //  获取单元格中的值
            for (Cell cell : rol) {
                System.out.println(cell);
            }
        }
        excel.close();
    }




    @Test
    public void test2() throws IOException {
        // 打开工作簿
        XSSFWorkbook excel = new XSSFWorkbook(new FileInputStream("/home/lqs/下载/白银现货统计(1).xlsx"));
        // 选择工作表
        XSSFSheet sheet = excel.getSheetAt(0);
        // 获得最后一行的行号（索引）,行从0开始
        int lastRowNum = sheet.getLastRowNum();
        // 遍历行,通过行号来获取每一行
        for(int i = 0; i <= lastRowNum; i++){
            XSSFRow row = sheet.getRow(i);
            // 获取当前行最后一个单元格的索引
            short lastCellNum = row.getLastCellNum();
            for(int j = 0; j < lastCellNum; j++){
                XSSFCell cell = row.getCell(j);
                System.out.println(cell);
            }
        }
        excel.close();
    }


    @Test
    // 写入excel
    public void test3() throws Exception{
        // 在内存中创建一个excel文件
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        // 创建工作簿
        XSSFSheet liqisong = xssfWorkbook.createSheet("liqisong");
        // 创建工作表
        XSSFRow row = liqisong.createRow(0);
        row.createCell(0).setCellValue("name");
        row.createCell(1).setCellValue("age");

        XSSFRow row2 = liqisong.createRow(1);
        row2.createCell(0).setCellValue("liqisong");
        row2.createCell(1).setCellValue(20);

        // 利用输出写入文件，写入磁盘
        FileOutputStream file = new FileOutputStream(new File("/home/lqs/下载/白银现货统计(1).xlsx"));
        xssfWorkbook.write(file);



    }



}
