package com.tan.controller;

import com.tan.entity.EntityMonitorData;
import com.tan.entity.EntityPageBean;
import com.tan.entity.EntityResult;
import com.tan.service.ServiceMonitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by TanLiangJie
 * Time:2024/5/3 下午2:45
 */

/**
 * 检测信息
 */

@RestController
@RequestMapping("/api/monitors")
public class ControllerMonitor {

    @Autowired
    private ServiceMonitor serviceMonitor;

    /**
     * 获取检测数据
     * @param currentPage
     * @param pageSize
     * @return
     */

    @GetMapping
    public EntityResult list(@RequestParam(defaultValue = "1") Integer currentPage, @RequestParam(defaultValue = "10") Integer pageSize){
        EntityPageBean<EntityMonitorData> datas = serviceMonitor.page(currentPage,pageSize);
        return EntityResult.success(datas);
    }

}
