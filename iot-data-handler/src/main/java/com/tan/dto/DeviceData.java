package com.tan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by TanLiangJie
 * Time:2024/5/22 下午12:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceData {

    /**
     * 订阅资源名称
     */
    private String resource;
    /**
     * 事件--report
     */
    private String event;
    /**
     * 资源事件生成时间
     */
    private String event_time;
    /**
     * 推送消息
     */
    private NotifyData notify_data;


}
