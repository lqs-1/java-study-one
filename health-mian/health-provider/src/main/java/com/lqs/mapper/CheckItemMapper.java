package com.lqs.mapper;

import com.github.pagehelper.Page;
import com.lqs.pojo.CheckItem;

import java.util.List;

public interface CheckItemMapper {
    Boolean addCheckItem(CheckItem checkItem);
    Page<CheckItem> findBy(String queryString);
    int findCheckItemCheckGroup(int id);
    void deleteCheckItem(int id);
    CheckItem findById(Integer id);
    void editCheckItem(CheckItem checkItem);
    List<CheckItem> findAll();
}
