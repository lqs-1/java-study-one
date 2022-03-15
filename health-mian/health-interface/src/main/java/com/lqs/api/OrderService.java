package com.lqs.api;

import com.lqs.entity.Result;
import com.lqs.pojo.Order;
import com.lqs.pojo.OrderSetting;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface OrderService {
    void excelInfoUpload(List<OrderSetting> orderSettingList) throws IOException;

    List<Map> getOrderSettingBy(String date);

    void editNumberByDate(OrderSetting orderSetting);

    Result addOrder(Map map) throws Exception;

    Map<String, Object> findById(Integer id);

    void deleteOrderSetting();
}
