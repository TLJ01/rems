package com.tan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by TanLiangJie
 * Time:2024/5/3 下午3:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Device {
    private Integer deviceId; // 设备ID
    private Integer patientId; // 患者ID
    private Integer isDeleted; // 是否删除-->0未删除  1已删除
}
