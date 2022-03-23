package com.lqs.test.document;

import com.lqs.elast.ElasticSearchApplication;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;


/**
 * 查询数据用get
 */

@SpringBootTest(classes = ElasticSearchApplication.class)
@RunWith(SpringRunner.class)
public class Test1 {

    @Autowired
    private RestHighLevelClient restHighLevelClient;


    /**
     * 第一步：导坐标
     * 第二步：配置appplication.yml,配置地址和端口
     * 第三步：写一个配置类，将创建客户端的bean放到spring容器维护
     * 第四步：自动装配使用
     * @throws IOException
     */

    @Test
    public void test11() throws IOException, InterruptedException {
        // 构建请求
        GetRequest getRequest = new GetRequest("book", "1");
        // ==========可以设置请求参数===========
        // 第一个：控制返回的数据拥有哪些字段,include是想要的字段，exclude是不想要的字段
        String[] param = new String[]{"name","price"};
        FetchSourceContext fetchSourceContext = new FetchSourceContext(true, param,null);
        getRequest.fetchSourceContext(fetchSourceContext);
        // 设置路由，还不懂
        getRequest.routing("routing");



        // 执行
        // 同步查询
        // GetResponse documentFields = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        // 打印数据
        // System.out.println(documentFields.getId());
        // System.out.println(documentFields.getVersion());
        // System.out.println(documentFields.getIndex());
        // System.out.println(documentFields.getSourceAsString());



        // 执行
        // 异步查询
            // 创建监听器，重写方法
        ActionListener<GetResponse> actionListener = new ActionListener<GetResponse>() {
            @Override
            public void onResponse(GetResponse documentFields) {
                // 成功的时候打印结果
                    // 判断是否有数据
                if(documentFields.isExists()){
                    System.out.println(documentFields.getVersion());
                    System.out.println(documentFields.getId());
                    System.out.println(documentFields.getSourceAsString());
                    System.out.println(documentFields.getSourceAsMap());
                }else{
                    System.out.println("error");
                }
            }
            @Override
            public void onFailure(Exception e) {
                // 失败的时候执行
                e.printStackTrace();
                System.out.println("fail");
            }
        };
        restHighLevelClient.getAsync(getRequest,RequestOptions.DEFAULT,actionListener);
        Thread.sleep(5000);  // 睡5秒,防止客户端实例立即关闭

    }
}
