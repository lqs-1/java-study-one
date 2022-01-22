package com.lqs.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class TypeHandler extends BaseTypeHandler<Date> {
    @Override
    // 将java数据转换成mysql需要的类型
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Date date, JdbcType jdbcType) throws SQLException {
        long time = date.getTime();
        preparedStatement.setLong(i,time);
    }

    @Override
    // 将mysql类型转换成java类型
    public Date getNullableResult(ResultSet resultSet, String s) throws SQLException {
        // 获取结果集中需要转换的数据
        long aLong = resultSet.getLong(s);
        return new Date(aLong);
    }

    @Override
    public Date getNullableResult(ResultSet resultSet, int i) throws SQLException {
        // 获取结果集中需要转换的数据
        long aLong = resultSet.getLong(i);
        return new Date(aLong);
    }

    @Override
    public Date getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        long aLong = callableStatement.getLong(i);
        return new Date(aLong);
    }
}
