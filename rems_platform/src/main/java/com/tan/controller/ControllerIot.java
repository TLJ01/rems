package com.tan.controller;

import com.google.gson.Gson;
import com.tan.dto.DeviceData;
import com.tan.dto.DevicePropertyV5;
import com.tan.entity.EntityResult;
import com.tan.service.ServiceIot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        //存入数据
        serviceIot.save(map);
        return EntityResult.success();
    }
}
