package com.lqs.api;

import com.aliyuncs.exceptions.ClientException;
import com.lqs.entity.PageResult;
import com.lqs.entity.QueryPageBean;
import com.lqs.entity.Result;
import com.lqs.pojo.Setmeal;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

public interface SetmealService {

    Result uploadFile(String originalFilename, byte[] fileBytes);

    void addSetmeal(Integer[] checkgroupIds, Setmeal setmeal);

    PageResult findBy(QueryPageBean queryPageBean);

    List<Setmeal> findAllSetmeal();

    Setmeal findById(Integer id);

}
