package com.tan.interceptor;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.tan.entity.EntityDoctor;
import com.tan.utils.UserThreadLocal;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by TanLiangJie
 * Time:2024/5/8 下午7:03
 */

public class InterceptorLogin implements HandlerInterceptor {

    private StringRedisTemplate stringRedisTemplate;

    public InterceptorLogin(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //获取请求头中的token
        String token = request.getHeader("Authorization");
        if (StrUtil.isBlank(token)) {
            response.setStatus(401);
            System.out.println("-----------111");
            return false;
        }

        //基于token获取redis中的对象
        Map<Object, Object> userMap = stringRedisTemplate.opsForHash().entries("login:doctor:" + token);


        if (userMap.isEmpty()) {
            response.setStatus(401);
            System.out.println("-----------222");
            return false;
        }

        //将查询到的数据转为DTO
        EntityDoctor entityDoctor = BeanUtil.fillBeanWithMap(userMap, new EntityDoctor(), false);

        //存在,保存用户到线程中
        UserThreadLocal.put(entityDoctor);

        //刷新token的有效期
        stringRedisTemplate.expire( "login:doctor:"+ token, 30L, TimeUnit.MINUTES);
        //放行
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
