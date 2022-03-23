package com.lqs.test.document;


import com.lqs.elast.ElasticSearchApplication;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 修改用:
 *  第一种就是添加的时候替换
 *  第二种update,如果存在就是修改，如果不存在就是创建
 * */
@SpringBootTest(classes = ElasticSearchApplication.class)
@RunWith(SpringRunner.class)
public class Test3 {


    @Autowired
    private RestHighLevelClient client;


//    POST /test_index/_doc/5/_update
//    {
//        "doc":{
//        "price":33333333
//    }
//    }



    @Test
    public void test33() throws IOException, InterruptedException {
        // 构建请求
            // 请求头  POST /test_index/_doc/5/_update
        UpdateRequest request = new UpdateRequest("test_index", "3");

            // 创建要修改文档,和添加的时候一样有3种方式
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("price", 1986666666);

        request.doc(jsonMap);






        // 执行
            // 同步
        // UpdateResponse updateResponse = client.update(request, RequestOptions.DEFAULT);
        // 打印结果
        // if (updateResponse.getResult() == DocWriteResponse.Result.CREATED){
        //    DocWriteResponse.Result result = updateResponse.getResult();
        //    System.out.println(result);
        // }else if(updateResponse.getResult() == DocWriteResponse.Result.UPDATED){
        // DocWriteResponse.Result result = updateResponse.getResult();
        // System.out.println(result);
        // }else{
        //    System.out.println("fail");
        // }

            // 异步
        ActionListener<UpdateResponse> listener = new ActionListener<>() {
            @Override
            public void onResponse(UpdateResponse updateResponse) {
                // 成功后执行的语句
                if (updateResponse.getResult() == DocWriteResponse.Result.UPDATED){
                    System.out.println(updateResponse.getResult());
                }else if(updateResponse.getResult() == DocWriteResponse.Result.CREATED){
                    System.out.println(updateResponse.getResult());
                }else{
                    System.out.println("fail");
                }

                // 判断分片是否完整的存储了数据
                ReplicationResponse.ShardInfo shardInfo = updateResponse.getShardInfo();
                if (shardInfo.getTotal() > shardInfo.getSuccessful()){
                    System.out.println("分片并未完全存储");
                }

                // 打印分片未完全存储的原因
                if(shardInfo.getFailed() > 0){
                    ReplicationResponse.ShardInfo.Failure[] failures = shardInfo.getFailures();
                    for (ReplicationResponse.ShardInfo.Failure failure : failures) {
                        String reason = failure.reason();
                        System.out.println(reason);
                    }
                }
            }

            @Override
            public void onFailure(Exception e) {
                // 失败后执行的语句
                e.printStackTrace();
                System.out.println("fail");
            }
        };
        client.updateAsync(request, RequestOptions.DEFAULT, listener);
        // 为了不让客户端在执行请求之后直接销毁，所以等待一下，不然直接报错，但是数据已经存储到了elasticsearch，只是程序里买你拿不到数据，
        Thread.sleep(5000);

    }
}
