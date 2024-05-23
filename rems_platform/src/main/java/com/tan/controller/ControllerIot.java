package com.tan.controller;

import com.google.gson.Gson;
import com.tan.dto.DeviceData;
import com.tan.dto.DevicePropertyV5;
import com.tan.entity.EntityResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/iot")
public class ControllerIot {

    @PostMapping("/deviceData")
    public EntityResult receiveDeviceData(@RequestBody Map map) {

        log.info("raw of map:{}",map);

        Gson gson = new Gson();
        String json = gson.toJson(map);
        DeviceData deviceData = gson.fromJson(json, DeviceData.class);
        log.info("DeviceData:{}",deviceData);
        for (DevicePropertyV5 service : deviceData.getNotify_data().getBody().getServices()) {
            System.out.println("血氧:"+service.getProperties().getBloodOxygen());
            System.out.println("心率:"+service.getProperties().getHeartRate());
            System.out.println("-----------------------------");
        }
        return EntityResult.success(deviceData);
    }
}
