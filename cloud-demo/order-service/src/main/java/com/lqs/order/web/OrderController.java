package com.lqs.order.web;

//import com.lqs.feign.client.UserClient;
//import com.lqs.feign.client.UserClient;
import com.lqs.feign.client.UserClient;
import com.lqs.feign.pojo.User;
import com.lqs.order.pojo.Order;
//import com.lqs.order.pojo.User;
import com.lqs.order.service.OrderService;
//import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
public class OrderController {


    @Autowired
    private UserClient userClient;

   @Autowired
   private OrderService orderService;



//   @Autowired
//   private RestTemplate restTemplate;

    @GetMapping("{orderId}")
    public Order queryOrderByUserId(@PathVariable("orderId") Long orderId, @RequestHeader(value = "lqs") String lqs) {
        System.out.println(lqs);
        // 根据id查询订单并返回
        Order order = orderService.queryOrderById(orderId);
        // 远程调用feign方式
        User user = userClient.findById(order.getUserId());
        // 远程调用RestTemplate方式
        // User user = restTemplate.getForObject("http://user-server/user/" + order.getUserId(), User.class);
        order.setUser(user);
        return order;
    }
}
