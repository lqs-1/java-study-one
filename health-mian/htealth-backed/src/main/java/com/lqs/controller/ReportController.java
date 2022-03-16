package com.lqs.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.lqs.api.ReportService;
import com.lqs.api.SetmealService;
import com.lqs.constant.MessageConstant;
import com.lqs.entity.Result;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "report")
public class ReportController {
    @Reference(version = "1.0")
    private ReportService reportService;

    @Reference(version = "1.0")
    private SetmealService setmealService;

    @GetMapping(value = "getMemberReport.do")
    public Result getMemberReport(){
        // 查询一年内会员的数量
        try{
            Result result = reportService.getMemberReport();
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_MEMBER_NUMBER_REPORT_FAIL);
        }
    }

    @GetMapping(value = "getSetmealReport.do")
    public Result getSetmealReport(){
        // 套餐信息统计
        // 这个map用来存放所有的数据
        Map<String, Object> data = new HashMap<>();

        // 图例和具体饼图数据分开
        // 用来存储图例
        List<String> titleName = new ArrayList<>();
        // 用来存储饼图数据
        List<Map<String, Object>> resultData;
        try{
            resultData = setmealService.findSetMealCount();
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_SETMEAL_COUNT_REPORT_FAIL);
        }
        // 根据饼图数据抽出套餐名字
        for (Map<String, Object> resultDatum : resultData) {
            String name = (String) resultDatum.get("name");
            titleName.add(name);
        }
        data.put("setmealName", titleName);
        data.put("setmealCount", resultData);

        return new Result(true, MessageConstant.GET_SETMEAL_COUNT_REPORT_SUCCESS, data);
    }


    @GetMapping(value = "getBusinessReportData.do")
    public Result getBusinessReportData(){
        // 获取的数据很多，运营数据
        try{
            Map<String, Object> resultMap = reportService.getBusinessReportData();
            return new Result(true, MessageConstant.GET_BUSINESS_REPORT_SUCCESS, resultMap);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_BUSINESS_REPORT_FAIL);
        }
    }


    // 导出为excel表格报表
    @GetMapping(value = "exportBusinessReport.do")
    public Result getExportBusinessReport(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取模板文件的绝对路径,File.separator表示分割符号 因为windows和linux的分割符号不一样所有用这个api
        String excelPath = request.getSession().getServletContext().getRealPath("template") + File.separator + "report_template.xlsx";
        // 获取模板文件的的excel输入流
        XSSFWorkbook excel = new XSSFWorkbook(new FileInputStream(new File(excelPath)));
        Map<String, Object> resultMap;
        try{
             resultMap = reportService.getBusinessReportData();
        } catch (Exception exception) {
            exception.printStackTrace();
            return new Result(false, MessageConstant.GET_BUSINESS_REPORT_FAIL);
        }
        String reportDate = (String) resultMap.get("reportDate");
        Integer todayNewMember = (Integer) resultMap.get("todayNewMember");
        Integer totalMember = (Integer) resultMap.get("totalMember");
        Integer thisWeekNewMember = (Integer) resultMap.get("thisWeekNewMember");
        Integer thisMonthNewMember = (Integer) resultMap.get("thisMonthNewMember");
        Integer todayOrderNumber = (Integer) resultMap.get("todayOrderNumber");
        Integer todayVisitsNumber = (Integer) resultMap.get("todayVisitsNumber");
        Integer thisWeekOrderNumber = (Integer) resultMap.get("thisWeekOrderNumber");
        Integer thisWeekVisitsNumber = (Integer) resultMap.get("thisWeekVisitsNumber");
        Integer thisMonthOrderNumber = (Integer) resultMap.get("thisMonthOrderNumber");
        Integer thisMonthVisitsNumber = (Integer) resultMap.get("thisMonthVisitsNumber");
        List<Map<String, Object>> hotSetmeal = (List<Map<String, Object>>) resultMap.get("hotSetmeal");

        // 写入文件
        XSSFSheet sheet = excel.getSheetAt(0);
        // 写入数据
        sheet.getRow(2).getCell(5).setCellValue(reportDate);

        sheet.getRow(4).getCell(5).setCellValue(todayNewMember);
        sheet.getRow(4).getCell(7).setCellValue(totalMember);
        sheet.getRow(5).getCell(5).setCellValue(thisWeekNewMember);
        sheet.getRow(5).getCell(7).setCellValue(thisMonthNewMember);

        sheet.getRow(7).getCell(5).setCellValue(todayOrderNumber);
        sheet.getRow(7).getCell(7).setCellValue(todayVisitsNumber);
        sheet.getRow(8).getCell(5).setCellValue(thisWeekOrderNumber);
        sheet.getRow(8).getCell(7).setCellValue(thisWeekVisitsNumber);
        sheet.getRow(9).getCell(5).setCellValue(thisMonthOrderNumber);
        sheet.getRow(9).getCell(7).setCellValue(thisMonthVisitsNumber);

        // 写入最受欢迎的套餐
        Integer row = 12;
        for (Map<String, Object> setmeal: hotSetmeal){
            sheet.getRow(row).getCell(4).setCellValue((String) setmeal.get("name"));
//            sheet.getRow(row).getCell(5).setCellValue(Integer.parseInt((String) setmeal.get("setmeal_count")));
            row += 1;
        }

        // 获取输出流
        OutputStream out = response.getOutputStream();
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("content-Disposition", "attachment;filename=report.xlsx");
        excel.write(out);
        out.flush();
        out.close();
        excel.close();

        return null;
    }


}
