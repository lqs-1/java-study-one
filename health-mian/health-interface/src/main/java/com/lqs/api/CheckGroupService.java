package com.lqs.api;

import com.lqs.entity.PageResult;
import com.lqs.entity.QueryPageBean;
import com.lqs.pojo.CheckGroup;

import java.util.List;
import java.util.Map;


public interface CheckGroupService {
    void addCheckGroup(Integer[] checkitemIds, CheckGroup checkGroup);

    PageResult findCheckGroupBy(QueryPageBean queryPageBean);

    void deleteCheckGroup(Integer id);

   Map<String, Object> findCheckGroupById(Integer id);

    void editCheckGroup(CheckGroup checkGroup, Integer[] checkitemIds);

    List<CheckGroup> findAllCheckGroup();
}
