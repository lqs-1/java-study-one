package com.lqs.mapper;

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
}
