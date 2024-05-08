package com.tan.service;

import com.tan.entity.EntityMonitorData;
import com.tan.entity.EntityPageBean;

public interface ServiceMonitor{

    /**
     * 获取检测数据
     * @return
     */
    EntityPageBean<EntityMonitorData> page(Integer currentPage, Integer pageSize);
}
