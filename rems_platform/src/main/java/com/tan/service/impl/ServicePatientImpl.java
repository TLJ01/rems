package com.tan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tan.dto.DtoPatientSave;
import com.tan.dto.DtoPatientUpdate;
import com.tan.mapper.MapperPatient;
import com.tan.pojo.PageBean;
import com.tan.pojo.Patient;
import com.tan.service.ServicePatient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by TanLiangJie
 * Time:2024/5/3 下午1:48
 */
@Service
@Slf4j
public class ServicePatientImpl implements ServicePatient {

    @Autowired
    private MapperPatient mapperPatient;

    /**
     * 获取病人列表
     * @return
     */
    @Override
    public PageBean page(Integer currentPage, Integer pageSize) {
        IPage<Patient> page = new Page<>(currentPage, pageSize);
        //查询条件,isDeleted=0,未被删除的
        LambdaQueryWrapper<Patient> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Patient::getIsDeleted,0);
        mapperPatient.selectPage(page, wrapper);
        return new PageBean(page.getTotal(),page.getRecords());
    }

    /**
     * 根据id获取病人信息
     * @param id
     * @return
     */
    @Override
    public Patient getById(Integer id) {
        return mapperPatient.selectById(id);
    }

    /**
     * 根据病人名字或者病型进行查询
     * @param key
     * @return
     */
    @Override
    public List<Patient> getByNameOrCategory(String key) {
        LambdaQueryWrapper<Patient> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Patient::getIsDeleted,0);
        //根据名字或者病型进行查询
        wrapper.like(Patient::getName,key).or().eq(Patient::getCategory,key);
        return mapperPatient.selectList(wrapper);
    }

    /**
     * 根据id删除病人信息
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        LambdaUpdateWrapper<Patient> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Patient::getId,id);
        wrapper.set(Patient::getIsDeleted,1);
        mapperPatient.update(wrapper);
    }

    /**
     * 新增病人
     * @param dtoPatient
     */
    @Override
    public void save(DtoPatientSave dtoPatient) {
        Patient patient = new Patient();
        BeanUtils.copyProperties(dtoPatient,patient);
        //这里可以改,预计30天后恢复,根据需求来
        patient.setRecoveryTime(LocalDate.now().plusDays(30));
        mapperPatient.insert(patient);
    }

    /**
     * 更新病人信息
     * @param dtoPatient
     */
    @Override
    public void update(DtoPatientUpdate dtoPatient) {
        Patient patient = new Patient();
        BeanUtils.copyProperties(dtoPatient,patient);
        mapperPatient.updateById(patient);
    }


}
