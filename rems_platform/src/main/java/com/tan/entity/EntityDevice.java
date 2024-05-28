package com.tan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Created by TanLiangJie
 * Time:2024/5/3 下午3:51
 * 设备实体
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("device_info")
public class EntityDevice {


    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 设备id
     */
    private String deviceId;

    /**
     * 患者id
     */
    private Integer patientId;

    /**
     * 设备状态
     * 1在线
     * 0不在线
     */
    private Integer status;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
