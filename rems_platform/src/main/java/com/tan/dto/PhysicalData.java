package com.tan.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Created by TanLiangJie
 * Time:2024/5/23 下午7:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("device_data")
public class PhysicalData {

    /**
     * 设备id
     */
    @TableId
    private String deviceId;

    /**
     * 患者id
     */
    private String patientId;

    /**
     * 心率
     */
    private Integer heartRate;
    /**
     * 血氧
     */
    private Integer bloodOxygen;

    private LocalDateTime updateTime;


}
