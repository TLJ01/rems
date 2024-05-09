package com.tan.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.tan.constant.EntityResponseConstants;
import com.tan.entity.EntityResult;
import com.tan.service.ServiceEmail;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import static com.tan.constant.RedisConstants.*;

/**
 * Created by TanLiangJie
 * Time:2024/5/8 上午10:44
 *
 *
 * 邮箱验证服务层
 */
@Service
public class ServiceEmailmpl implements ServiceEmail {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired(required = false)
    private JavaMailSender sender; // 引入Spring Mail依赖后，会自动装配到IOC容器。用来发送邮件

    /**
     * 发送验证码
     *
     * @param mail
     * @param request
     * @return
     */
    @Override
    public EntityResult send(String mail, HttpServletRequest request) {

        try{
            // 生成 6 位数字验证码
            String code = RandomUtil.randomNumbers(6);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject("【测试验证码】验证消息"); // 发送邮件的标题
            message.setText("登录操作，验证码："+ code + "，切勿将验证码泄露给他人，本条验证码有效期2分钟。"); // 发送邮件的内容
            message.setTo(mail); // 指定要接收邮件的用户邮箱账号
            message.setFrom("2914421833@qq.com"); // 发送邮件的邮箱账号，注意一定要和配置文件中的一致！

            sender.send(message); // 调用send方法发送邮件即可

            stringRedisTemplate.opsForValue().set(REGISTER_CODE_KEY + mail, code,2L, TimeUnit.MINUTES);

            return EntityResult.success(EntityResponseConstants.SUCCESS);
        }
        catch (Exception e){
            return EntityResult.error(EntityResponseConstants.FAIL);
        }


    }
}
