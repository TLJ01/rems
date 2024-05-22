package com.tan.controller;

import com.tan.entity.EntityResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/iot")
public class IoTDataController {

    private static final Logger logger = LoggerFactory.getLogger(IoTDataController.class);

    @PostMapping("/deviceData")
    public EntityResult receiveDeviceData(@RequestBody Map<String, Object> data) {
        logger.info("Received data from Huawei IoT platform: {}", data);
        System.out.println(data.toString());
        // 处理接收到的数据，例如存储到数据库或进行进一步处理
//        return "Data received successfully";
        return EntityResult.success(data);
    }
}
