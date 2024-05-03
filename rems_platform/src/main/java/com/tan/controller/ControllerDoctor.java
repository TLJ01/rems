package com.tan.controller;

import com.tan.pojo.Doctor;
import com.tan.pojo.PageBean;
import com.tan.pojo.Patient;
import com.tan.pojo.Result;
import com.tan.service.ServiceDoctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by TanLiangJie
 * Time:2024/5/3 下午2:45
 */
@RestController
@RequestMapping("/doctors")
public class ControllerDoctor {

    @Autowired
    private ServiceDoctor serviceDoctor;

    /**
     * 获取医生列表
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage,@RequestParam(defaultValue = "5") Integer pageSize){
        PageBean pageBean = serviceDoctor.page(currentPage,pageSize);
        return Result.success(pageBean);
    }

    /**
     * 根据id查询医生信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Doctor doctor = serviceDoctor.getById(id);
        return Result.success(doctor);
    }


}
