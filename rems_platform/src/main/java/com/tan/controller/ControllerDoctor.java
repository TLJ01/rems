package com.tan.controller;

import com.tan.dto.DtoDoctorLogin;
import com.tan.dto.DtoDoctorRegister;
import com.tan.entity.EntityDoctor;
import com.tan.entity.EntityPageBean;
import com.tan.entity.EntityResult;
import com.tan.service.ServiceDoctor;
import com.tan.utils.UserThreadLocal;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Created by TanLiangJie
 * Time:2024/5/3 下午2:45
 */
@Slf4j
@RestController
@RequestMapping("/api/doctors")
@CrossOrigin
public class ControllerDoctor {

    @Autowired
    private ServiceDoctor serviceDoctor;



    /**
     * 登录
     * @param //dtoDoctorLogin
     * @return
     */
    @PostMapping("/login")
    public EntityResult login(@RequestBody DtoDoctorLogin dtoDoctorLogin){
        return serviceDoctor.login(dtoDoctorLogin);
    }
//    @PostMapping("/login")
//    public EntityResult login(String username,String password){
//        EntityResult result = serviceDoctor.login(username, password);
//        log.info("返回数据：{}",result);
//        return result;
//    }


    /**
     * 注册
     * @param dtoDoctorRegister
     * @return
     */
    @PostMapping("/register")
    public EntityResult register(@RequestBody DtoDoctorRegister dtoDoctorRegister, HttpServletRequest request){
        return serviceDoctor.register(dtoDoctorRegister,request);
    }


    /**
     * 退出登录
     * @return
     */
    @GetMapping("/logout")
    public EntityResult logout(HttpServletRequest request){
        return serviceDoctor.logout(request);
    }

    /**
     * 可以用来展示右上角当前医生信息之类的
     * @return
     */
    @GetMapping("/index")
    public EntityResult index(){
        EntityDoctor entityDoctor = UserThreadLocal.get();
        return EntityResult.success(entityDoctor);
    }

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
