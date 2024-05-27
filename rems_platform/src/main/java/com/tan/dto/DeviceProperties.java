package com.tan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by TanLiangJie
 * Time:2024/5/23 下午3:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceProperties {

    /**
     * 心率
     */
    private Integer heartRate;
    /**
     * 血氧
     */
    private Double bloodOxygen;


}
