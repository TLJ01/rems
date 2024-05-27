package com.tan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by TanLiangJie
 * Time:2024/5/22 下午12:35
 */


/**
 * {
 *   "deviceId": "device001",
 *   "services": [
 *     {
 *       "serviceId": "HeartRateService",
 *       "properties": {
 *         "heartRate": 75,
 *         "bloodOxygen": 98.5
 *       },
 *       "eventTime": "2024-05-01T08:00:00Z"
 *     }
 *   ]
 * }
 */


/**
 * {
 *   "resource": "device.property",
 *   "event": "report",
 *   "event_time": "string",
 *   "notify_data": {
 *     "header": {
 *       "app_id": "d4922d8a-6c8e-4396-852c-164aefa6638f",
 *       "device_id": "d4922d8a-6c8e-4396-852c-164aefa6638f",
 *       "node_id": "ABC123456789",
 *       "product_id": "ABC123456789"
 *     },
 *     "body": {
 *       "services": [
 *         {
 *           "service_id": "string",
 *           "properties": {"heartRate": 75,
 *                           "bloodOxygen": 98.5
 *                           },
 *           "event_time": "string"
 *         }
 *       ]
 *     }
 *   }
 * }
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
