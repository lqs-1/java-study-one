package com.lqs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@Controller

@RequestMapping("upload")
public class Upload_file {

    // 单文件
    @RequestMapping(value = "index1")
    @ResponseBody
    public void save(String username, MultipartFile upload) throws IOException {
        // 获取文件名称
        String filename = upload.getOriginalFilename();
        upload.transferTo(new File("/home/lqs/"+filename));
    }

    // 多文件，就是多个file类型的标签和多个multipartFile的变量
    // 或者前端书写同样的键，后端编写数组来接

}
