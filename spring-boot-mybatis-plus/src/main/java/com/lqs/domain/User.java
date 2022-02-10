package com.lqs.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
public class User {

    private int id;
    private String name;
    private int money;

}
