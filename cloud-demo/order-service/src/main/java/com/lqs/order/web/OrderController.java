package com.lqs.order.web;

import com.lqs.order.pojo.Order;
import com.lqs.order.pojo.User;
import com.lqs.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("order")
public class OrderController {

   @Autowired
   private OrderService orderService;

   @Autowired
   private RestTemplate restTemplate;

    @GetMapping("{orderId}")
    public Order queryOrderByUserId(@PathVariable("orderId") Long orderId) {

        // 根据id查询订单并返回
        Order order = orderService.queryOrderById(orderId);
        // 远程调用
        User user = restTemplate.getForObject("http://127.0.0.1:8081/user/" + order.getUserId(), User.class);
        order.setUser(user);
        return order;
    }
}
