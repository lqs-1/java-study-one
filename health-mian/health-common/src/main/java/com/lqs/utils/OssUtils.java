package com.lqs.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.comm.ResponseMessage;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.aliyun.oss.model.VoidResult;
import com.lqs.constant.MessageConstant;
import com.lqs.entity.Result;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class OssUtils {

    private static String endpoint = "https://oss-cn-chengdu.aliyuncs.com";
    private static String accessKeyId = "LTAI5tMMRUVcNmfWrM5RjUyX";
    private static String accessKeySecret = "Fchr5xqVXddeT869ptwf6TfthZ9tOE";
    private static String bucketName = "health-main";
    private static String bucketDomain = "https://health-main.oss-cn-chengdu.aliyuncs.com";

    public static Result UpfileToOss(String originFileName, InputStream fileStream){


//        System.out.println(originFileName);
//        System.out.println(fileStream);

        // 链接Oss,创建oss实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 生成上传文件的目录
//        String folderName = new SimpleDateFormat("yyyyMMdd").format(new Date());


        // 生成上传文件在OSS服务器上保存的文件名
        // 原始文件名：xxx.jpg
        // 生成文件名：isdjfoi32fuha8097ya87sd6f7283.jpg
        // 使用UUID生成文件主体名称
//        String fileMainName = UUID.randomUUID().toString().replace("-", "");



        // 获取文件后缀
//        String extensionName = originFileName.substring(originFileName.lastIndexOf("."));

        // 使用目录、文件主体名称、文件扩展名称拼接得到对象名称
//        String objectName = folderName + "/" + fileMainName + extensionName;




        try{
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, originFileName, fileStream);
            PutObjectResult putObjectResult = ossClient.putObject(putObjectRequest);
            ResponseMessage response = putObjectResult.getResponse();

            if(response == null){
                String resultUrl = bucketDomain + "/" + originFileName;
                return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS, resultUrl);
            }else {
                                // 获取响应状态码
                int statusCode = response.getStatusCode();

                // 如果请求没有成功，获取错误消息
                String errorMessage = response.getErrorResponseAsString();

                // 当前方法返回失败
                return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
            }
        }catch (Exception e){

            e.printStackTrace();
            return new Result(false, "connection fail");

        }finally {

            ossClient.shutdown();

        }
    }


    public static void ClearFile(String fileName){
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        VoidResult voidResult = ossClient.deleteObject(bucketName, fileName);
    }




    public static void main(String[] args) throws FileNotFoundException {
//        InputStream im = new FileInputStream("/home/lqs/client.conf");
//        System.out.println(im);
//        Result haha = OssUtils.UpfileToOss("haha.conf", im);
//        System.out.println(haha.getData());
    }
}
