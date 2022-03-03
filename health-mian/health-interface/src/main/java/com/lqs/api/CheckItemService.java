package com.lqs.api;

import com.lqs.entity.PageResult;
import com.lqs.entity.QueryPageBean;
import com.lqs.pojo.CheckItem;

public interface CheckItemService {
    Boolean addCheckItem(CheckItem checkItem);
    PageResult findPage(QueryPageBean queryPageBean);
    int findCheckItemCheckGroup(int id);
    void deleteCheckItem(int id);
    CheckItem findById(Integer id);
    void editCheckItem(CheckItem checkItem);
}
