package com.lqs.mapper;

import com.github.pagehelper.Page;
import com.lqs.pojo.CheckGroup;

import java.util.List;
import java.util.Map;

public interface CheckGroupMapper {
    void addCheckGroup(CheckGroup checkGroup);
    void addCheckGroupCheckItem(Map<String, Integer> checkGroupCheckItem);
    // 分页查询
    Page<CheckGroup> findCheckGroupBy(String queryString);

    void deleteCheckGroup(Integer id);

    void deleteCheckGroupCheckItem(Integer id);

    CheckGroup findCheckGroupById(Integer id);

    List<Integer> findCheckGroupCheckItemById(Integer id);

    void editCheckGroup(CheckGroup checkGroup);
}
