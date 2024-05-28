package com.tan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tan.entity.EntityDevice;
import org.apache.ibatis.annotations.Select;


public interface MapperDevice extends BaseMapper<EntityDevice> {

    @Select("select device_info.patient_id from ywyl_sh.device_info where device_id = #{deviceId}")
    Integer selectPatIdByDevId(String deviceId);
}
