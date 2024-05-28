package com.tan.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by TanLiangJie
 * Time:2024/5/28 上午9:57
 *
 * 查询检测数据的dto
 */
@Data
public class DtoMonitorQuery {

    /**
     * 设备id
     */
    private String deviceId;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;


}
