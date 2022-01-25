package com.lqs.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
// 使用mybatis-plus时候，指定数据库表
@TableName("user")
public class User {
    private int id;
    private String name;
    private int money;
}
