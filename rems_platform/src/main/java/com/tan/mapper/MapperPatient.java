package com.tan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tan.entity.EntityPatient;
import org.apache.ibatis.annotations.Select;
//

public interface MapperPatient extends BaseMapper<EntityPatient>{
    /**
     * 查询名字
     * @param patientId
     */
    @Select("select name from ywyl_sh.patient_info where id = #{patientId}")
    String selectNameById(Integer patientId);

//    /**
//     * 查询患者及设备状态
//     * @return
//     */
//
//    Page<EntityPatient> pageQuery(DtoPatientQuery dtoPatientQuery);
}
