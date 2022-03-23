package com.lqs.test.document;

import com.lqs.elast.ElasticSearchApplication;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
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
 * 批量操作使用bulk，就是里面装了很多很多的请求
 * */
@SpringBootTest(classes = ElasticSearchApplication.class)
@RunWith(SpringRunner.class)
public class Test5 {

    @Autowired
    private RestHighLevelClient client;


    @Test
    public void test555() throws IOException {
        // 构造请求
        BulkRequest bulkRequest = new BulkRequest();
        // 添加一个添加文档的请求，添加两条文档
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("name", "liqisong");
        bulkRequest.add(new IndexRequest("test_index").id("10").source(jsonMap));
        bulkRequest.add(new IndexRequest("test_index").id("11").source(jsonMap));

        // 添加一个，修改文档的请求
        Map<String, Object> jsonMap1 = new HashMap<>();
        jsonMap1.put("name", "lqs");
        bulkRequest.add(new UpdateRequest("test_index", "10").doc(jsonMap1));


        // 添加一个，删除文档的请求
        bulkRequest.add(new DeleteRequest("test_index", "10"));



        // 执行
        BulkResponse bulkResponse = client.bulk(bulkRequest, RequestOptions.DEFAULT);

        // 打印结果
        for (BulkItemResponse bulkItemResponse : bulkResponse) {
            // 拿到批量操作中的每一个请求
            DocWriteResponse response = bulkItemResponse.getResponse();
            // 利用分支语句判断操作的类型
            switch (bulkItemResponse.getOpType()){
                case INDEX:
                    // 拿到具体的请求的response进行强转
                    IndexResponse indexResponse = (IndexResponse) response;
                    System.out.println(indexResponse.getResult());
                    break;
                case CREATE:
                    IndexResponse createResponse = (IndexResponse) response;
                    System.out.println(createResponse.getResult());
                    break;
                case UPDATE:
                    UpdateResponse updateResponse = (UpdateResponse) response;
                    System.out.println(updateResponse.getResult());
                    break;
                case DELETE:
                    DeleteResponse deleteResponse = (DeleteResponse) response;
                    System.out.println(deleteResponse.getResult());
                    break;
            }
        }


    }



}
