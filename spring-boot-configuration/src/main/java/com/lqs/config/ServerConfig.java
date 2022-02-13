package com.lqs.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

//@Component
@Data
@ConfigurationProperties(prefix = "servers")
// 开启对当前bean的属性注入校验
@Validated
public class ServerConfig {
    private String ipAddress;
    @Max(value = 6666, message = "最大越界")
    @Min(value = 1234, message = "最小越界")
    private int port;
    private Long timeout;

    /*
    * 常用的计量单位
    *   Duration: 时间的类型，可以用注解 @DurationUnit(ChronoUnit.xxx)来指定具体的时间
    *   DataSize：空间大小类型， 可以用 @DataSizeUnit(DataUnit.xxx)来指定具体的空间大小单位
     * */

    @DurationUnit(ChronoUnit.DAYS)
    private Duration day;
    @DataSizeUnit(DataUnit.GIGABYTES)
    private DataSize file;

}
