package com.lqs.test;

import com.lqs.elast.ElasticSearchApplication;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * 删除数据用delete
 */

@SpringBootTest(classes = ElasticSearchApplication.class)
@RunWith(SpringRunner.class)
public class Test4 {

    @Autowired
    private RestHighLevelClient client;


    // DELETE /test_index/_doc/6

    @Test
    public void test444() throws IOException {
        // 构建请求
        DeleteRequest request = new DeleteRequest("test_index", "6");


        // 执行
            // 同步
        // DeleteResponse deleteResponse = client.delete(request, RequestOptions.DEFAULT);
        // 打印数据
        // System.out.println(deleteResponse.getIndex());
        // System.out.println(deleteResponse.getId());
        // System.out.println(deleteResponse.getResult());

            // 异步
        ActionListener<DeleteResponse> listener = new ActionListener<>() {
            @Override
            public void onResponse(DeleteResponse deleteResponse) {
                // 删除成功执行的代码
                if(deleteResponse.getResult() == DocWriteResponse.Result.DELETED){
                    System.out.println(deleteResponse.getIndex());
                    System.out.println(deleteResponse.getId());
                    System.out.println(deleteResponse.getResult());
                }else if(deleteResponse.getResult() == DocWriteResponse.Result.NOT_FOUND){
                    System.out.println(deleteResponse.getResult());
                }else{
                    System.out.println("fail");
                }

                // 查看是否删除干净，分片情况查看
                ReplicationResponse.ShardInfo shardInfo = deleteResponse.getShardInfo();
                if (shardInfo.getTotal() > shardInfo.getSuccessful()){
                    System.out.println("有些分片没有删除干净");
                }
                // 分片没有删除干净的原因
                if (shardInfo.getFailed() > 0){
                    ReplicationResponse.ShardInfo.Failure[] failures = shardInfo.getFailures();
                    for (ReplicationResponse.ShardInfo.Failure failure : failures) {
                        String reason = failure.reason();
                        System.out.println(reason);
                    }
                }
            }
            @Override
            public void onFailure(Exception e) {
                // 删除失败以后执行的代码
                e.printStackTrace();
                System.out.println("fail");
            }
        };
        client.deleteAsync(request, RequestOptions.DEFAULT, listener);
    }




}
