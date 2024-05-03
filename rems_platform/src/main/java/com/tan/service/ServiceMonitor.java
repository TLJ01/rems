package com.tan.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tan.pojo.MonitorData;
import com.tan.pojo.PageBean;

public interface ServiceMonitor{

    /**
     * 获取检测数据
     * @return
     */
    PageBean<MonitorData> page(Integer currentPage,Integer pageSize);
}
