package com.tan.controller;

import com.tan.dto.DtoDoctorLogin;
import com.tan.dto.DtoDoctorRegister;
import com.tan.entity.EntityDoctor;
import com.tan.entity.EntityPageBean;
import com.tan.entity.EntityResult;
import com.tan.service.ServiceDoctor;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
     * 登录
     * @param dtoDoctorLogin
     * @return
     */
    @PostMapping("/login")
    public EntityResult login(@RequestBody DtoDoctorLogin dtoDoctorLogin){
        return serviceDoctor.login(dtoDoctorLogin);
    }


    /**
     * 注册
     * @param dtoDoctorRegister
     * @return
     */
    @PostMapping("/register")
    public EntityResult register(@RequestBody DtoDoctorRegister dtoDoctorRegister, HttpServletRequest request){
        return serviceDoctor.register(dtoDoctorRegister,request);
    }


//    @GetMapping("/logout")
//    public EntityResult logout(){
//
//    }

    /**
     * 获取医生列表
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping
    public EntityResult list(@RequestParam(defaultValue = "1") Integer currentPage, @RequestParam(defaultValue = "5") Integer pageSize){
        EntityPageBean entityPageBean = serviceDoctor.page(currentPage,pageSize);
        return EntityResult.success(entityPageBean);
    }

    /**
     * 根据id查询医生信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public EntityResult getById(@PathVariable Integer id){
        EntityDoctor doctor = serviceDoctor.getById(id);
        return EntityResult.success(doctor);
    }


}
