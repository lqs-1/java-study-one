package com.lqs.api;

import com.lqs.pojo.OrderSetting;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface OrderService {
    void excelInfoUpload(List<OrderSetting> orderSettingList) throws IOException;

    List<Map> getOrderSettingBy(String date);

    void editNumberByDate(OrderSetting orderSetting);
}
