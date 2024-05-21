package com.tan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tan.dto.DtoPatientQuery;
import com.tan.entity.EntityPatient;
import com.tan.vo.VoPatient;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;
//

public interface MapperPatient extends BaseMapper<EntityPatient>{

//    /**
//     * 查询患者及设备状态
//     * @return
//     */
//
//    Page<EntityPatient> pageQuery(DtoPatientQuery dtoPatientQuery);
}
