package com.tan.service;

import com.tan.dto.DtoPatientQuery;
import com.tan.dto.DtoPatientSave;
import com.tan.dto.DtoPatientUpdate;
import com.tan.entity.EntityPageBean;
import com.tan.entity.EntityPatient;
import com.tan.entity.EntityResult;

import java.util.List;

public interface ServicePatient {
    /**
     * 获取病人的列表
     * @return
     */
    EntityPageBean page(DtoPatientQuery dtoPatientQuery);

    /**
     * 根据id获取病人信息
     *
     * @param id
     * @return
     */
    EntityResult getById(Integer id);


    /**
     * 根据id删除病人信息
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 新增病人
     *
     * @param dtoPatient
     * @return
     */
    EntityResult save(DtoPatientSave dtoPatient);

    /**
     * 更新病人信息
     * @param dtoPatient
     */
    void update(DtoPatientUpdate dtoPatient);
}
