package com.lqs.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


//@Order(-1)  这种方式和接口实现的完全一样，数字越小越优先
@Component  // 将这个类放到spring容器中
public class UserFilter implements GlobalFilter, Ordered {
    /*
    * 这个过滤器用于验证用户是否是admin，如果是就放行，如果不是就拦截
    * */

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取请求头
        ServerHttpRequest request = exchange.getRequest();
        // 获取所有请求参数
        MultiValueMap<String, String> params = request.getQueryParams();
        // 获取请求参数列表中的user
        String user = params.getFirst("user");
        // 判断参数是否等于admin
        System.out.println(user);
        if ("admin".equals(user)){
            // 是，放行
            return chain.filter(exchange);
        }
        // 不是拦截，设置状态码到响应头
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
