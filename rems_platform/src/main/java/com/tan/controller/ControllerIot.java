package com.tan.controller;

import com.tan.entity.EntityResult;
import com.tan.service.ServiceIot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/iot")
public class ControllerIot {

    @Autowired
    private ServiceIot serviceIot;
    /**
     * 接收存储数据
     * @param map
     * @return
     */
    @PostMapping("/deviceData")
    public EntityResult receiveDeviceData(@RequestBody Map map) {
        log.info("数据:{}",map);
        //存入数据
        serviceIot.save(map);
        return EntityResult.success();
    }

    /**
     * 获取检测数据
     * 晚上10点到早上6点
     * 15分钟一次
     * 30次左右
     * @return
     */
//    @GetMapping("/list")
//    public EntityResult list(@RequestBody DtoMonitorQuery dtoMonitorQuery){
//        return EntityResult.success(serviceIot.list(dtoMonitorQuery));
//    }
    @GetMapping("/list")
    public EntityResult list(@RequestParam String deviceId,String startTime,String endTime){
        return EntityResult.success(serviceIot.list(deviceId,startTime,endTime));
    }
}
