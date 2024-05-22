package com.tan.controller;

import com.tan.entity.EntityResult;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/iot")
public class IoTDataController {

    @PostMapping("/deviceData")
    public EntityResult receiveDeviceData(@RequestBody Map<String, Object> data) {
        log.info("Received data from Huawei IoT platform: {}", data);
        System.out.println(data.toString());
        // 处理接收到的数据，例如存储到数据库或进行进一步处理
        return EntityResult.success(data);
    }
}
