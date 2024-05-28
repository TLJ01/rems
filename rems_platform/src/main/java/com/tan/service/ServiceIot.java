package com.tan.service;

import com.tan.vo.VoMonitorData;

import java.time.LocalDateTime;
import java.util.Map;

public interface ServiceIot {

    /**
     * 接收存储数据1
     * @param map
     */
    void save(Map map);

//    /**
//     * 获取检测数据列表
//     * @param dtoMonitorQuery
//     * @return
//     */
//    VoMonitorData list(DtoMonitorQuery dtoMonitorQuery);

    /**
     * 获取检测数据列表
     *
     * @param
     * @param deviceId
     * @param startTime
     * @param endTime
     * @return
     */
    VoMonitorData list(String deviceId, String startTime, String endTime);
}
