package com.tan.service.impl;

import com.google.gson.Gson;
import com.tan.dto.DeviceData;
import com.tan.dto.DevicePropertyV5;
import com.tan.dto.PhysicalData;
import com.tan.mapper.MapperIot;
import com.tan.service.ServiceIot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Created by TanLiangJie
 * Time:2024/5/22 下午1:03
 */
@Service
public class ServiceIotImpl implements ServiceIot {

    @Autowired
    private MapperIot mapperIot;

    /**
     * 接收存储数据
     * @param map
     */
    @Override
    public void save(Map map) {

        Gson gson = new Gson();

        //序列化为json字符串
        String json = gson.toJson(map);
        //反序列化
        DeviceData deviceData = gson.fromJson(json, DeviceData.class);

        //解析数据

        //获取设备id
        String deviceId = deviceData.getNotify_data().getHeader().getDevice_id();

        //获取数据
        for (DevicePropertyV5 service : deviceData.getNotify_data().getBody().getServices()) {
            Integer heartRate = service.getProperties().getHeartRate();
            Integer bloodOxygen = service.getProperties().getBloodOxygen();
            PhysicalData physicalData = new PhysicalData();
            physicalData.setDeviceId(deviceId);
            physicalData.setHeartRate(heartRate);
            physicalData.setBloodOxygen(bloodOxygen);
            physicalData.setUpdateTime(LocalDateTime.now());
            physicalData.setPatientId("test111");
            mapperIot.insert(physicalData);
        }

    }
}
