package com.tan.service;

import com.tan.dto.DtoDoctorLogin;
import com.tan.dto.DtoDoctorRegister;
import com.tan.entity.EntityDoctor;
import com.tan.entity.EntityPageBean;
import com.tan.entity.EntityResult;
import jakarta.servlet.http.HttpServletRequest;

public interface ServiceDoctor{

    /**
     * 获取医生列表
     * @param currentPage
     * @param pageSize
     * @return
     */
    EntityPageBean page(Integer currentPage, Integer pageSize);

    /**
     * 根据id获取医生信息
     * @param id
     * @return
     */
    EntityDoctor getById(Integer id);

    /**
     * 登录
     *
     * @param dtoDoctorLogin
     */
    EntityResult login(DtoDoctorLogin dtoDoctorLogin);

    /**
     * 注册
     *
     * @param dtoDoctorRegister
     * @param request
     * @return
     */
    EntityResult register(DtoDoctorRegister dtoDoctorRegister, HttpServletRequest request);

    /**
     * 退出登录
     * @return
     */
    EntityResult logout(HttpServletRequest request);
}
