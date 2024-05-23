package com.tan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by TanLiangJie
 * Time:2024/5/22 下午12:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotifyDataHeader {
    /**
     * 应用ID
     */
    private String app_id;
    /**
     * 设备ID
     */
    private String device_id;
    /**
     * 设备标识码
     */
    private String node_id;
    /**
     * 产品ID
     */
    private String product_id;
}
