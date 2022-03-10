package com.lqs.mapper;

import com.lqs.pojo.Order;
import com.lqs.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderMapper {
    Integer findDateCount(String orderDate);

    void editOrderSetting(Map<String, Object> editMap);

    void addOrderSetting(OrderSetting orderSetting);

    void deleteOrderSetting();

    List<OrderSetting> getOrderSettingByMonth(Map<String, String> queryMap);

//    根据日期查找ordersetting对象
    OrderSetting findByOrderDate(Date date);
// 查询是否同一个人同一天预约列表
    List<Order> findByCondition(Order order);
// 保存Order
    void add(Order order);
// 修改ordersetting的已经预约人数
    void editReservationsByOrderDate(Map<String, Object> dateString);

    Order findById(Integer id);
}
