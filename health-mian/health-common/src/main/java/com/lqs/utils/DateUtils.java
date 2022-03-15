package com.lqs.utils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期操作工具类
 */
public class DateUtils {
    /**
     * 日期转换-  String -> Date
     *
     * @param dateString 字符串时间
     * @return Date类型信息
     * @throws Exception 抛出异常
     */
    public static Date parseString2Date(String dateString) throws Exception {
        if (dateString == null) {
            return null;
        }
        return parseString2Date(dateString, "yyyy-MM-dd");
    }

    /**
     * 日期转换-  String -> Date
     *
     * @param dateString 字符串时间
     * @param pattern    格式模板
     * @return Date类型信息
     * @throws Exception 抛出异常
     */
    public static Date parseString2Date(String dateString, String pattern) throws Exception {
        if (dateString == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = sdf.parse(dateString);
        return date;
    }

    /**
     * 日期转换 Date -> String
     *
     * @param date Date类型信息
     * @return 字符串时间
     * @throws Exception 抛出异常
     */
    public static String parseDate2String(Date date) throws Exception {
        if (date == null) {
            return null;
        }
        return parseDate2String(date, "yyyy-MM-dd");
    }

    /**
     * 日期转换 Date -> String
     *
     * @param date    Date类型信息
     * @param pattern 格式模板
     * @return 字符串时间
     * @throws Exception 抛出异常
     */
    public static String parseDate2String(Date date, String pattern) throws Exception {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String strDate = sdf.format(date);
        return strDate;
    }

    // 获取当前周的周一
    public static String getThisWeekFirstDay(){
        Calendar calendar = Calendar.getInstance();
        // 设置一周的第一天是周几
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        // 获取当前是这一周的第几天
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        // 根据日期来推算（1-x）
        calendar.add(Calendar.DATE, calendar.getFirstDayOfWeek() - weekDay);
        // 格式化返回
        return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
    }


    // 获取当前月的第一天
    public static String getThisMonthFirstDay(){
        Calendar calendar = Calendar.getInstance();
        // 设置日期为当前月的第一天
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        // 返回格式化数据
        return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
    }

    public static void main(String[] args) {
        String thisWeekFirstDay = DateUtils.getThisWeekFirstDay();
        System.out.println(thisWeekFirstDay);
        String thisMonthFirstDay = DateUtils.getThisMonthFirstDay();
        System.out.println(thisMonthFirstDay);
    }
}
