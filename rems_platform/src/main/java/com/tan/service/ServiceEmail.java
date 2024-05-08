package com.tan.service;

import com.tan.entity.EntityResult;
import jakarta.servlet.http.HttpServletRequest;

public interface ServiceEmail {
    /**
     * 发送验证码
     *
     * @param mail
     * @param request
     * @return
     */
    EntityResult send(String mail, HttpServletRequest request);
}
