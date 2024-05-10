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
    private Integer deviceId; // 设备ID
    private Integer heartRate; // 心率
    private Double bloodOxygen; // 血氧

    /**
     * 恢复进度,这里用1-10表示
     */
    private Integer recoverStatus;


    /**
     * 隔一段时间检测一次,该怎么做
     */
    private LocalDateTime measureTime; // 测量时间

}
