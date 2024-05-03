package com.tan.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tan.mapper.MapperMonitor;
import com.tan.pojo.Doctor;
import com.tan.pojo.MonitorData;
import com.tan.pojo.PageBean;
import com.tan.service.ServiceMonitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by TanLiangJie
 * Time:2024/5/3 下午3:50
 */
@Service
public class ServiceMonitorImpl implements ServiceMonitor {

    @Autowired
    private MapperMonitor mapperMonitor;

    /**
     * 获取检测数据
     * @return
     */
    @Override
    public PageBean<MonitorData> page(Integer currentPage, Integer pageSize) {
        IPage<MonitorData> page = new Page<>(currentPage, pageSize);
        mapperMonitor.selectPage(page,null);
        return new PageBean(page.getTotal(),page.getRecords());
    }
}
