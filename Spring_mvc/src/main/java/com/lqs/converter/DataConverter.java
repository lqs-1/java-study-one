package com.lqs.converter;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// 自定义一个转换器，用于时间字符串和日期格式的统一格式
public class DataConverter implements Converter<String, Date> {

    @Override
    public Date convert(String source) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateStr = null;
        try {
            dateStr = format.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateStr;
    }
}
