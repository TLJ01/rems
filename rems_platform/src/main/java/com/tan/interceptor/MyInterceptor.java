package com.tan.interceptor;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.tan.entity.EntityDoctor;
import com.tan.utils.UserThreadLocal;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.tan.constant.RedisConstants.LOGIN_DOCTOR_KEY;

/**
 * Created by TanLiangJie
 * Time:2024/5/8 下午7:03
 *
 *
 * 拦截器
 */
@Slf4j
public class MyInterceptor implements HandlerInterceptor {

    private StringRedisTemplate stringRedisTemplate;

    public MyInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //获取请求头中的token
        String token = request.getHeader("Authorization");
        if (StrUtil.isBlank(token)) {
//            response.setStatus(401);
//            return false;
            log.info("hello被拦截了");
            throw new RuntimeException("当前未登录");
        }

        //基于token获取redis中的对象
        Map<Object, Object> userMap = stringRedisTemplate.opsForHash().entries(LOGIN_DOCTOR_KEY + token);


        if (userMap.isEmpty()) {
            //response.setStatus(401);
            //response.getWriter().write("当前未登录");
            throw new RuntimeException("token失效,请重新登录");
            //return false;
        }

        //将查询到的数据转为DTO
        EntityDoctor entityDoctor = BeanUtil.fillBeanWithMap(userMap, new EntityDoctor(), false);

        //存在,保存用户到线程中---->快速获取用户信息
        //因为每次请求都是一个不同的线程,所以需要在拦截添加信息
        UserThreadLocal.put(entityDoctor);

        //刷新token的有效期
        stringRedisTemplate.expire( LOGIN_DOCTOR_KEY+ token, 30L, TimeUnit.MINUTES);
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
