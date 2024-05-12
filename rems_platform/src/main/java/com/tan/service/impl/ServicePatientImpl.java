package com.tan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tan.constant.EntityResponseConstants;
import com.tan.dto.DtoPatientSave;
import com.tan.dto.DtoPatientUpdate;
import com.tan.entity.EntityResult;
import com.tan.mapper.MapperPatient;
import com.tan.entity.EntityPageBean;
import com.tan.entity.EntityPatient;
import com.tan.service.ServicePatient;
import com.tan.utils.UserThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by TanLiangJie
 * Time:2024/5/3 下午1:48
 *
 *
 * 患者服务层
 */
@Service
@Slf4j
public class ServicePatientImpl implements ServicePatient {

    @Autowired
    private MapperPatient mapperPatient;


    /**
     * 获取医生id
     * @return
     */
    public Integer getDoctorId(){
        return UserThreadLocal.get().getId();
    }

    /**
     * 获取病人列表---->每位医生查看自己的病人
     * @return
     */
    @Override
    public EntityPageBean page(Integer currentPage, Integer pageSize) {

        IPage<EntityPatient> page = new Page<>(currentPage, pageSize);
        //查询条件,isDeleted=0,未被删除的
        LambdaQueryWrapper<EntityPatient> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EntityPatient::getIsDeleted,0);

        //当前医生自己的病人
        wrapper.eq(EntityPatient::getDoctorId,getDoctorId());

        mapperPatient.selectPage(page, wrapper);
        return new EntityPageBean(page.getTotal(),page.getRecords());
    }

    /**
     * 根据id获取病人信息
     *
     * @param id
     * @return
     */
    @Override
    public EntityResult getById(Integer id) {
        LambdaQueryWrapper<EntityPatient> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EntityPatient::getDoctorId,getDoctorId());
        wrapper.eq(EntityPatient::getId,id);
        return mapperPatient.selectOne(wrapper)==null?EntityResult.error("没有权限"):EntityResult.success(mapperPatient.selectOne(wrapper));
    }

    /**
     * 根据病人名字或者病型进行查询
     * @param key
     * @return
     */
    @Override
    public List<EntityPatient> getByNameOrCategory(String key) {
        LambdaQueryWrapper<EntityPatient> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EntityPatient::getIsDeleted,0);
        //根据名字或者病型进行查询
        wrapper.like(EntityPatient::getName,key).or().eq(EntityPatient::getCategory,key);
        return mapperPatient.selectList(wrapper);
    }

    /**
     * 根据id删除病人信息
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        LambdaUpdateWrapper<EntityPatient> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(EntityPatient::getId,id);
        wrapper.set(EntityPatient::getIsDeleted,1);
        mapperPatient.update(wrapper);
    }

    /**
     * 新增病人
     *
     * @param dtoPatient
     * @return
     */
    @Override
    public EntityResult save(DtoPatientSave dtoPatient) {
        /**
         * 这里如果用户姓名已存在,就会返回一些异常信息,手动处理一下
         */
        String name = dtoPatient.getName();
        LambdaQueryWrapper<EntityPatient> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EntityPatient::getName,name);
        EntityPatient entityPatient = mapperPatient.selectOne(wrapper);
        if (entityPatient != null) {
            return EntityResult.error(EntityResponseConstants.USER_ISEXIST);
        }

        EntityPatient patient = new EntityPatient();
        BeanUtils.copyProperties(dtoPatient,patient);
        //这里可以改,预计30天后恢复,根据需求来
        patient.setRecoveryTime(LocalDate.now().plusDays(30));

        mapperPatient.insert(patient);
        return EntityResult.success(EntityResponseConstants.SUCCESS);
    }

    /**
     * 更新病人信息
     * @param dtoPatient
     */
    @Override
    public void update(DtoPatientUpdate dtoPatient) {
        EntityPatient patient = new EntityPatient();
        BeanUtils.copyProperties(dtoPatient,patient);
        mapperPatient.updateById(patient);
    }


}
