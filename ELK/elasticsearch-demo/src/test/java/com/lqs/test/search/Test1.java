package com.lqs.test.search;


import com.lqs.elast.ElasticSearchApplication;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Map;

@SpringBootTest(classes = ElasticSearchApplication.class)
@RunWith(SpringRunner.class)
public class Test1 {

    @Autowired
    private RestHighLevelClient client;

    // 搜索全部记录
    @Test
    public void testSearchAll() throws IOException {
        // 创建请求
        SearchRequest searchRequest = new SearchRequest("book");
        // 设置搜索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        // 获取某些字段
        searchSourceBuilder.fetchSource(new String[]{"name"}, null);
        // 结合
        searchRequest.source(searchSourceBuilder);


        // 执行搜索
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);


        // 打印结果
        SearchHits hits = searchResponse.getHits();
        SearchHit[] dataHits = hits.getHits();
        for (SearchHit dataHit : dataHits) {
            String id = dataHit.getId();
            float score = dataHit.getScore();
            Map<String, Object> sourceAsMap = dataHit.getSourceAsMap();
            String name = (String) sourceAsMap.get("name");
            System.out.println(name);
        }
    }



    // 搜索的分页
    @Test
    public void searchByPage() throws IOException {
        // 构建请求
        SearchRequest searchRequest = new SearchRequest("book");
        // 搜索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        // 搜索结果返回的字段
        searchSourceBuilder.fetchSource(new String[]{"name"}, null);
        // 分页,也就是只显示某一些数据,from从多少条。size到多少条
        searchSourceBuilder.from(1);
        searchSourceBuilder.size(1);
        // 结合
        searchRequest.source(searchSourceBuilder);



        // 执行
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);


        // 获取数据
        SearchHits hits = searchResponse.getHits();
        SearchHit[] hits1 = hits.getHits();
        for (SearchHit documentFields : hits1) {
            Map<String, Object> sourceAsMap = documentFields.getSourceAsMap();
            String name = (String) sourceAsMap.get("name");
            System.out.println(name);
        }
    }



    // ids搜索
    @Test
    public void searchByIds() throws IOException {
        // 构建请求
        SearchRequest searchRequest = new SearchRequest("book");
        // 搜索的条件,根据id来
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.idsQuery().addIds("1", "100"));
        // 设置请求返回的字段
        searchSourceBuilder.fetchSource(new String[]{"name"}, null);
        // 结合
        searchRequest.source(searchSourceBuilder);


        // 执行
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);


        // 获取数据
        SearchHits hits = searchResponse.getHits();
        SearchHit[] hits1 = hits.getHits();
        for (SearchHit documentFields : hits1) {
            Map<String, Object> sourceAsMap = documentFields.getSourceAsMap();
            String name = (String) sourceAsMap.get("name");
            System.out.println(name);
        }
    }



    // 关键字搜索(match搜索)
    @Test
    public void searchByKeyWords() throws IOException {
        // 构建请求
        SearchRequest searchRequest = new SearchRequest("book");
        // 搜索的条件,根据id来
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 只能在一个字段中搜索
        searchSourceBuilder.query(QueryBuilders.matchQuery("name", "lqs"));
        // 设置请求返回的字段
        searchSourceBuilder.fetchSource(new String[]{"name"}, null);
        // 结合
        searchRequest.source(searchSourceBuilder);


        // 执行
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);


        // 获取数据
        SearchHits hits = searchResponse.getHits();
        SearchHit[] hits1 = hits.getHits();
        for (SearchHit documentFields : hits1) {
            Map<String, Object> sourceAsMap = documentFields.getSourceAsMap();
            String name = (String) sourceAsMap.get("name");
            System.out.println(name);
        }
    }



    // 关键字搜索(multi_match搜索)
    @Test
    public void searchByKeyWordsTwo() throws IOException {
        // 构建请求
        SearchRequest searchRequest = new SearchRequest("book");
        // 搜索的条件,根据id来
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 可以在多个字段中搜索
        searchSourceBuilder.query(QueryBuilders.multiMatchQuery("lqs", "name", "desc"));
        // 设置请求返回的字段
        searchSourceBuilder.fetchSource(new String[]{"name"}, null);
        // 结合
        searchRequest.source(searchSourceBuilder);


        // 执行
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);


        // 获取数据
        SearchHits hits = searchResponse.getHits();
        SearchHit[] hits1 = hits.getHits();
        for (SearchHit documentFields : hits1) {
            Map<String, Object> sourceAsMap = documentFields.getSourceAsMap();
            String name = (String) sourceAsMap.get("name");
            System.out.println(name);
        }
    }


    // bool搜索
    @Test
    public void searchByBool() throws IOException {
        // 构建请求
        SearchRequest searchRequest = new SearchRequest("book");
        // 搜索的条件,根据id来
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // bool搜索是表示，必须满足和应该满足，强制和不强制
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("name", "liqisong");
        MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("name", "lqs");
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(matchQueryBuilder);
        boolQueryBuilder.should(matchQuery);
        // 放入
        searchSourceBuilder.query(boolQueryBuilder);
        // 设置请求返回的字段
        searchSourceBuilder.fetchSource(new String[]{"name"}, null);
        // 结合
        searchRequest.source(searchSourceBuilder);


        // 执行
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);


        // 获取数据
        SearchHits hits = searchResponse.getHits();
        SearchHit[] hits1 = hits.getHits();
        for (SearchHit documentFields : hits1) {
            Map<String, Object> sourceAsMap = documentFields.getSourceAsMap();
            String name = (String) sourceAsMap.get("name");
            System.out.println(name);
        }
    }


    // filter(过滤，基于bool)搜索
    @Test
    public void filterSearch() throws IOException {
        // 构建请求
        SearchRequest searchRequest = new SearchRequest("book");
        // 搜索的条件,根据id来
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // bool搜索是表示，必须满足和应该满足，强制和不强制
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("name", "liqisong");
        MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("name", "lqs");
        RangeQueryBuilder queryBuilder = QueryBuilders.rangeQuery("name").gte(50).lte(100);
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(matchQueryBuilder);
        boolQueryBuilder.should(matchQuery);
        boolQueryBuilder.filter(queryBuilder);
        // 放入
        searchSourceBuilder.query(boolQueryBuilder);
        // 设置请求返回的字段
        searchSourceBuilder.fetchSource(new String[]{"name"}, null);
        // 结合
        searchRequest.source(searchSourceBuilder);


        // 执行
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);


        // 获取数据
        SearchHits hits = searchResponse.getHits();
        SearchHit[] hits1 = hits.getHits();
        for (SearchHit documentFields : hits1) {
            Map<String, Object> sourceAsMap = documentFields.getSourceAsMap();
            String name = (String) sourceAsMap.get("name");
            System.out.println(name);
        }
    }




    // sort搜索
    @Test
    public void SortSearch() throws IOException {
        // 构建请求
        SearchRequest searchRequest = new SearchRequest("book");
        // 查询条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        // 设置请求返回的字段
        searchSourceBuilder.fetchSource(new String[]{"name"}, null);
        // 排序,text类型的不能排序
        searchSourceBuilder.sort("name", SortOrder.DESC);
        // 结合
        searchRequest.source(searchSourceBuilder);


        // 执行
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);


        // 获取数据
        SearchHits hits = searchResponse.getHits();
        SearchHit[] hits1 = hits.getHits();
        for (SearchHit documentFields : hits1) {
            Map<String, Object> sourceAsMap = documentFields.getSourceAsMap();
            String name = (String) sourceAsMap.get("name");
            System.out.println(name);
        }
    }








}
