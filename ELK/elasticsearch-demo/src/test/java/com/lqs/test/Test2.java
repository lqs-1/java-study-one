package com.lqs.test;


import com.lqs.elast.ElasticSearchApplication;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.VersionType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * 添加数据用index，如果存在就是就是覆盖，如果不存在就是添加
 *
 * */

@SpringBootTest(classes = ElasticSearchApplication.class)
@RunWith(SpringRunner.class)
public class Test2 {

    @Autowired
    private RestHighLevelClient restHighLevelClient;
//        PUT /test_index/_doc/1
//        {
//            "name":"abc",
//            "desc":"fjwoiajfiwjfaowjfoaif",
//            "price":123
//        }

    @Test
    public void test22() throws IOException, InterruptedException {
        // 构建请求
            // 请求头： PUT /test_index/_doc/1
        IndexRequest request = new IndexRequest("test_index");
        request.id("4");
            // 文档数据： json串
        // 方法一：
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("name", "lqs");
        jsonMap.put("desc", "普通人");
        jsonMap.put("price", "10");
        request.source(jsonMap);
        // 方法二：
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject(); // json上方
        {
            builder.field("name", "lqs");
            builder.field("desc", "普通人");
            builder.field("price", "10");
        }
        builder.endObject();  // json下方
        // 方法三：
        request.source("name", "lqs",
                "desc", "普通人",
                "price", "10");

        // =========可选参数=========
        request.timeout("1s");  // 设置超时时间，1s , 方式1
        request.timeout(TimeValue.timeValueSeconds(1));  // 设置超时时间，1s , 方式2

//        request.version(2); // 设置版本号
//        request.versionType(VersionType.EXTERNAL); // 设置手动维护版本号，外部维护






        // 执行
            // 同步
        // 获取结果
        // System.out.println(indexResponse.getIndex());
        // System.out.println(indexResponse.getId());
        // System.out.println(indexResponse.getResult());// IndexResponse indexResponse = restHighLevelClient.index(request, RequestOptions.DEFAULT);

            // 异步
        ActionListener<IndexResponse> listener = new ActionListener<>() {

            @Override
            public void onResponse(IndexResponse indexResponse) {
                // 添加成功
                if(indexResponse.getResult() == DocWriteResponse.Result.CREATED){
                    System.out.println(indexResponse.getIndex());
                    System.out.println(indexResponse.getId());
                    System.out.println(indexResponse.getResult());
                }else if(indexResponse.getResult() == DocWriteResponse.Result.UPDATED){
                    System.out.println(indexResponse.getIndex());
                    System.out.println(indexResponse.getId());
                    System.out.println(indexResponse.getResult());
                } else{
                    System.out.println("fail");
                }


                // 查看插入分片状态,有的时候有些分片坏掉了，所有在保存到保存的时候操作
                ReplicationResponse.ShardInfo shardInfo = indexResponse.getShardInfo();
                if (shardInfo.getTotal() != shardInfo.getSuccessful()){
                    System.out.println("处理成功的分片数量少于总分片数量");
                }
                // 如果失败的分片大于0,就便利失败的原因
                if (shardInfo.getFailed() > 0){
                    for (ReplicationResponse.ShardInfo.Failure failure : shardInfo.getFailures()) {
                        String reason = failure.reason(); // 没一个错误的原因
                        System.out.println(reason);
                    }
                }

            }

            @Override
            public void onFailure(Exception e) {
                // 添加失败
                e.printStackTrace();
                System.out.println("fail");
            }
        };
        restHighLevelClient.indexAsync(request,RequestOptions.DEFAULT,listener);
        Thread.sleep(5000);  // 怕执行完以后直接关闭，所以睡5秒
    }





}
