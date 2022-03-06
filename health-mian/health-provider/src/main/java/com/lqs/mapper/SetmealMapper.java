package com.lqs.mapper;

import com.github.pagehelper.Page;
import com.lqs.pojo.Setmeal;

import java.util.Map;

public interface SetmealMapper {
    void addSetmeal(Setmeal setmeal);
    void addSetmealCheckGroup(Map<String, Integer> setMealCheckGroup);

    Page<Setmeal> findBy(String queryString);
}
