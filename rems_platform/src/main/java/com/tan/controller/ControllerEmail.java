package com.tan.controller;

import com.tan.entity.EntityResult;
import com.tan.service.ServiceEmail;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by TanLiangJie
 * Time:2024/5/8 上午10:31
 */
@RestController
@RequestMapping("/api/email")
public class ControllerEmail {


    @Autowired
    private ServiceEmail serviceEmail;

    /**
     * 发送验证码
     * @param mail
     * @param request
     * @return
     */
    @PostMapping("/send")
    public EntityResult test1(String mail, HttpServletRequest request){
        return serviceEmail.send(mail,request);
    }


}


