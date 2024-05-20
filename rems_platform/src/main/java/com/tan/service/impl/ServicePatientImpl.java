package com.tan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tan.constant.EntityResponseConstants;
import com.tan.dto.DtoPatientQuery;
import com.tan.dto.DtoPatientSave;
import com.tan.dto.DtoPatientUpdate;
import com.tan.entity.EntityDevice;
import com.tan.entity.EntityResult;
import com.tan.mapper.MapperDevice;
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

    @Autowired
    private MapperDevice mapperDevice;



    /**
     * 获取医生id---->每位医生查看自己的病人
     * @return
     */
    public Integer getDoctorId(){
        return UserThreadLocal.get().getId();
    }

    /**
     * 获取病人列表
     * @return
     */
    @Override
    public EntityPageBean page(DtoPatientQuery dtoPatientQuery) {

        //获取参数
        Integer currentPage = dtoPatientQuery.getCurrentPage();
        Integer pageSize = dtoPatientQuery.getPageSize();
        String name = dtoPatientQuery.getName();
        String category = dtoPatientQuery.getCategory();

        //处理参数-->赋初值
        if (currentPage==0&&pageSize==0){
            currentPage=1;pageSize=5;
        }

        IPage<EntityPatient> page = new Page<>(currentPage, pageSize);
        //查询条件,isDeleted=0,未被删除的
        LambdaQueryWrapper<EntityPatient> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EntityPatient::getIsDeleted,0);

        //当前医生自己的病人
        wrapper.eq(EntityPatient::getDoctorId,getDoctorId());

        //模糊查询-->根据名字/病型
        if (category !=null) wrapper.like(EntityPatient::getCategory, category);
        if (name !=null) wrapper.like(EntityPatient::getName, name);

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
     * 根据id删除病人信息
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        LambdaUpdateWrapper<EntityPatient> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(EntityPatient::getId,id);
        wrapper.set(EntityPatient::getIsDeleted,1);

        wrapper.eq(EntityPatient::getDoctorId,getDoctorId());

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


        //这里可以插入患者的医生id
        patient.setDoctorId(getDoctorId());

        //这里可以改,预计30天后恢复,根据需求来
        patient.setRecoveryTime(LocalDate.now().plusDays(30));
        //插入患者数据
        mapperPatient.insert(patient);

        //获取患者id
        Integer patientId = patient.getId();

        //存入设备中
        //构造device实体对象
        EntityDevice device = EntityDevice.builder()
                .patientId(patientId).status(0).build();
        mapperDevice.insert(device);

        return EntityResult.success(EntityResponseConstants.SUCCESS);
    }

    /**
     * 更新病人信息
     * @param dtoPatient
     *
     * 该操作不需要判断是否是该患者的医生,因为显示出来就一定是,前面已经判断过了
     */
    @Override
    public void update(DtoPatientUpdate dtoPatient) {

        EntityPatient patient = new EntityPatient();
        BeanUtils.copyProperties(dtoPatient,patient);
        mapperPatient.updateById(patient);
    }


}
