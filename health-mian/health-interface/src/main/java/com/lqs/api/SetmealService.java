package com.lqs.api;

import com.lqs.entity.PageResult;
import com.lqs.entity.QueryPageBean;
import com.lqs.entity.Result;
import com.lqs.pojo.Setmeal;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public interface SetmealService {

    Result uploadFile(String originalFilename, byte[] fileBytes);

    void addSetmeal(Integer[] checkgroupIds, Setmeal setmeal);

    PageResult findBy(QueryPageBean queryPageBean);
}
