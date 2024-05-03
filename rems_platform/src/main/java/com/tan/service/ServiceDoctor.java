package com.tan.service;

import com.tan.pojo.Doctor;
import com.tan.pojo.PageBean;

public interface ServiceDoctor {

    /**
     * 获取医生列表
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageBean page(Integer currentPage, Integer pageSize);

    /**
     * 根据id获取医生信息
     * @param id
     * @return
     */
    Doctor getById(Integer id);
}
