package com.tan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by TanLiangJie
 * Time:2024/5/3 下午3:51
 *
 * 设备实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("device_info")
public class EntityDevice {
    /**
     * 设备id
     */
    @TableId(type = IdType.AUTO)
    private Integer deviceId;
    /**
     * 患者id
     */
    private Integer patientId;
    /**
     * 状态,1表示正常,0表示关闭
     */
    private Integer status;
}
