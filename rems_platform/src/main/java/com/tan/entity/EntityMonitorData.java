package com.tan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Created by TanLiangJie
 * Time:2024/5/3 下午4:06
 *
 * 检测数据实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("patient_monitoring_data")
public class EntityMonitorData {

    @TableId(type = IdType.AUTO)
    private Integer id; // 数据ID
    private Integer patientId; // 患者ID
    private LocalDateTime measureTime; // 测量时间
    private Integer deviceId; // 设备ID
    private Double bloodPressure; // 血压
    private Double bloodSugar; // 血糖
    private Integer heartRate; // 心率

}
