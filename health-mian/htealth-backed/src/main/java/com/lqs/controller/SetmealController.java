package com.lqs.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lqs.api.SetmealService;
import com.lqs.constant.MessageConstant;
import com.lqs.constant.RedisConstant;
import com.lqs.entity.PageResult;
import com.lqs.entity.QueryPageBean;
import com.lqs.entity.Result;
import com.lqs.pojo.Setmeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.UUID;


@RestController
@RequestMapping(value = "setmeal")
public class SetmealController {

    @Reference(version = "1.0")
    private SetmealService setmealService;

    @Autowired
    private JedisPool jedisPool;


    // 上传图片，回调oss仓库中url
    @PostMapping(value = "upload.do")
    /*
    * dubbo之间的通信之间没有输入输出流的共享，不能直接进行数据流的传输，需要拿到字节数组之后，转换成流
    * */
    public Result uploadImage(@RequestParam("imgFile") MultipartFile imgFile) throws IOException {
        String originalFilename = imgFile.getOriginalFilename();
        // 获取文件后缀
        String extensionName = originalFilename.substring(originalFilename.lastIndexOf("."));
        // 使用UUID生成文件主体名称
        String fileMainName = UUID.randomUUID().toString().replace("-", "");
        // 拼接文件名
        String fileName = fileMainName + extensionName;
        // 获取数据，后续转成InputStream
        byte[] fileBytes = imgFile.getBytes();
        Result result;
        try{
            System.out.println(fileName);
            result = setmealService.uploadFile(fileName, fileBytes);
//            String  data = (String) result.getData();
//            String filename = data.substring(data.lastIndexOf("/"));
//            System.out.println(fileName);
            // 所有上传到阿里云的图片，包含存到数据库中的，还有不在数据库中的，之后做一个定时任务清理未保存的图片
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES, fileName);
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }

        return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
    }

    // 添加套餐
    @PostMapping(value = "add.do")
    public Result addSetmeal(Integer[] checkgroupIds, @RequestBody Setmeal setmeal){

        try{

            setmealService.addSetmeal(checkgroupIds, setmeal);



        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_SETMEAL_FAIL);
        }
        return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
    }

    // 分页
    @PostMapping(value = "findPage.do")
    public PageResult findBy(@RequestBody QueryPageBean queryPageBean){
        return setmealService.findBy(queryPageBean);
    }
}
