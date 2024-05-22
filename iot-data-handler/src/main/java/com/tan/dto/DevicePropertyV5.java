package com.tan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by TanLiangJie
 * Time:2024/5/22 下午12:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DevicePropertyV5 {


    /**
     * 设备的服务ID
     */
    private String service_id;
    /**
     * 设备上报的数据
     */
    private Object properties;
    /**
     * 设备数据上报的时间
     * 格式取决于设备侧上报属性格式，
     * 支持的秒级格式：yyyyMMdd'T'HHmmss'Z'，毫秒级格式：yyyy-MM-dd'T'HH:mm:ss.SSS'Z'，
     * 例如20151212T121212Z或者2020-08-12T12:12:12.333Z。
     */
    private String event_time;

}
