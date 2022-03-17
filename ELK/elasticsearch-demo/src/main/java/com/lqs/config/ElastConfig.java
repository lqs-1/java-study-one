package com.lqs.config;


import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElastConfig {

    @Value("${lqs.service.host}")
    private String host;


    // 用了立马关闭客户端
    @Bean(destroyMethod = "close")
    public RestHighLevelClient restHighLevelClient(){
        String[] ht = host.split(",");
        HttpHost[] httpHosts = new HttpHost[ht.length];
        for(int i=0; i<ht.length; i++){
            String item = ht[i];
            httpHosts[i] = new HttpHost(item.split(":")[0],Integer.parseInt(item.split(":")[1]),"http");
        }
        return new RestHighLevelClient(RestClient.builder(httpHosts));
    }
}
