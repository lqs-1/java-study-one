package com.lqs.job;

import com.lqs.constant.RedisConstant;
import com.lqs.utils.OssUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.util.Set;

public class ClearImgJob {
    @Autowired
    private JedisPool jedisPool;

    //清理图片
    public void clearImg(){
        System.out.println("定时清理垃圾图片");
        Set<String> set = jedisPool.getResource().sdiff(RedisConstant.SETMEAL_PIC_RESOURCES, RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        if(set != null){
            for (String fileName : set) {
                System.out.println("定时清理垃圾图片： " + fileName);
                //根据图片名称从aliyun服务器删除文件
                OssUtils.ClearFile(fileName);
                //从redis集合中删除图片名称
                jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES,fileName);
            }
        }
    }

}
