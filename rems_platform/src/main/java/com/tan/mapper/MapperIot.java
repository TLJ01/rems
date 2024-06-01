package com.tan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tan.dto.DeviceProperties;
import com.tan.entity.EntityMonitorData;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MapperIot extends BaseMapper<EntityMonitorData> {
//    @Select("select * from ywyl_sh.monitor_data where device_id=#{deviceId} " +
//            "and monitor_data.measure_time between #{startTime} and #{endTime} " +
//            "order by measure_time asc")
//    List<DeviceProperties> list(DtoMonitorQuery dtoMonitorQuery);

    @Select("select * from ywyl_sh.monitor_data where device_id=#{deviceId} " +
            "and monitor_data.measure_time between #{startTime} and #{endTime} " +
            "order by measure_time asc")
    List<DeviceProperties> list(String deviceId, String startTime, String endTime);
}
