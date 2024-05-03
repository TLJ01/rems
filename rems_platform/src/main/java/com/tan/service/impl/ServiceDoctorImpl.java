package com.tan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tan.mapper.MapperDoctor;
import com.tan.pojo.Doctor;
import com.tan.pojo.PageBean;
import com.tan.pojo.Patient;
import com.tan.service.ServiceDoctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by TanLiangJie
 * Time:2024/5/3 下午2:46
 */
@Service
public class ServiceDoctorImpl implements ServiceDoctor {

    @Autowired
    private MapperDoctor mapperDoctor;

    /**
     * 获取医生列表
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public PageBean page(Integer currentPage, Integer pageSize) {
        IPage<Doctor> page = new Page<>(currentPage, pageSize);
        mapperDoctor.selectPage(page,null);
        return new PageBean(page.getTotal(),page.getRecords());
    }

    /**
     * 根据id获取医生信息
     * @param id
     * @return
     */
    @Override
    public Doctor getById(Integer id) {
        return mapperDoctor.selectById(id);
    }
}
