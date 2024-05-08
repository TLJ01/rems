package com.tan.controller;

import com.tan.entity.EntityResult;
import com.tan.service.ServiceEmail;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.parser.Entity;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;

/**
 * Created by TanLiangJie
 * Time:2024/5/8 上午10:31
 */
@RestController
@RequestMapping("/email")
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


