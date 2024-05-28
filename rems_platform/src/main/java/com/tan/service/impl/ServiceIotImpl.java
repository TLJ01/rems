package com.tan.service.impl;

import com.google.gson.Gson;
import com.tan.dto.DeviceData;
import com.tan.dto.DeviceProperties;
import com.tan.dto.DevicePropertyV5;
import com.tan.dto.DtoMonitorQuery;
import com.tan.entity.EntityDevice;
import com.tan.entity.EntityMonitorData;
import com.tan.mapper.MapperDevice;
import com.tan.mapper.MapperIot;
import com.tan.mapper.MapperPatient;
import com.tan.service.ServiceIot;
import com.tan.vo.VoMonitorData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Created by TanLiangJie
 * Time:2024/5/22 下午1:03
 */
@Service
public class ServiceIotImpl implements ServiceIot {

    @Autowired
    private MapperIot mapperIot;

    @Autowired
    private MapperDevice mapperDevice;

    @Autowired
    private MapperPatient mapperPatient;

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
        //根据设备id查询患者id
        Integer patientId = mapperDevice.selectPatIdByDevId(deviceId);


        //获取数据
        for (DevicePropertyV5 service : deviceData.getNotify_data().getBody().getServices()) {
            Integer heartRate = service.getProperties().getHeartRate();
            Double bloodOxygen = service.getProperties().getBloodOxygen();
            EntityMonitorData entityMonitorData = new EntityMonitorData();
            entityMonitorData.setDeviceId(deviceId);
            entityMonitorData.setHeartRate(heartRate);
            entityMonitorData.setBloodOxygen(bloodOxygen);
            entityMonitorData.setMeasureTime(LocalDateTime.now());
            entityMonitorData.setPatientId(patientId);
            mapperIot.insert(entityMonitorData);
        }

    }

    @Override
    public VoMonitorData list(String deviceId, String startTime, String endTime) {


        //查询名字
        Integer patientId = mapperDevice.selectPatIdByDevId(deviceId);
        String name = mapperPatient.selectNameById(patientId);

        /**
         * 手动查询,因为需要按时间排个序
         */
        List<DeviceProperties> list = mapperIot.list(deviceId,startTime,endTime);

        return new VoMonitorData(name,list);

    }


//    /**
//     * 获取数据列表
//     * @param deviceId,startTime,endTime
//     * @return
//     */
//    @Override
//    public VoMonitorData list(DtoMonitorQuery dtoMonitorQuery) {
//
//        //查询名字
//        Integer patientId = mapperDevice.selectPatIdByDevId(dtoMonitorQuery.getDeviceId());
//        String name = mapperPatient.selectNameById(patientId);
//
////        LocalDateTime startTime = LocalDateTime.parse(dtoMonitorQuery.getStartTime());
////        LocalDateTime endTime = LocalDateTime.parse(dtoMonitorQuery.getEndTime());
//
//        /**
//         * 手动查询,因为需要按时间排个序
//         */
//        List<DeviceProperties> list = mapperIot.list(dtoMonitorQuery);
//
//        return new VoMonitorData(name,list);
//    }
}
