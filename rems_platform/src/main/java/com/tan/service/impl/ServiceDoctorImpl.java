package com.tan.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tan.dto.DtoDoctorLogin;
import com.tan.dto.DtoDoctorRegister;
import com.tan.entity.EntityResult;
import com.tan.mapper.MapperDoctor;
import com.tan.entity.EntityDoctor;
import com.tan.entity.EntityPageBean;
import com.tan.service.ServiceDoctor;
import com.tan.utils.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Created by TanLiangJie
 * Time:2024/5/3 下午2:46
 */
@Slf4j
@Service
public class ServiceDoctorImpl implements ServiceDoctor {

    @Autowired
    private MapperDoctor mapperDoctor;


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 获取医生列表
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public EntityPageBean page(Integer currentPage, Integer pageSize) {
        IPage<EntityDoctor> page = new Page<>(currentPage, pageSize);
        mapperDoctor.selectPage(page, null);
        return new EntityPageBean(page.getTotal(), page.getRecords());
    }

    /**
     * 根据id获取医生信息
     *
     * @param id
     * @return
     */
    @Override
    public EntityDoctor getById(Integer id) {
        return mapperDoctor.selectById(id);
    }


    /**
     * 登录
     *
     * @param dtoDoctorLogin
     */
    @Override
    public EntityResult login(DtoDoctorLogin dtoDoctorLogin) {
        String username = dtoDoctorLogin.getUsername();
        //查询该用户是否存在
        LambdaQueryWrapper<EntityDoctor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EntityDoctor::getUsername, username);
        EntityDoctor user = mapperDoctor.selectOne(queryWrapper);
        //不存在,抛出异常
        if (Objects.isNull(user)) {
            return EntityResult.error("该用户不存在");
        }


        //存在,校验密码
        if (user.getPassword().equals(Md5Util.getMD5String(dtoDoctorLogin.getPassword()))) {
            //用户名和密码正确,生成jwt
            Map<String, Object> claims = new HashMap<>();
            claims.put("username", username);
            claims.put("id", user.getId());
            String jwt = JwtUtils.generateJwt(claims);
            HashMap<String, String> map = new HashMap<>();
            map.put("token", jwt);

            //将用户信息存入redis;token作为key,用户信息作为value
            //将UserDto转为map
            Map<String, Object> userMap = BeanUtil.beanToMap(user, new HashMap<>(),
                    CopyOptions.create()
                            .setIgnoreNullValue(true)
                            .setFieldValueEditor((fieldName, fieldValue) -> fieldValue.toString()));

            stringRedisTemplate.opsForHash().putAll("login:doctor:" + jwt, userMap);
            stringRedisTemplate.expire("login:doctor:" + jwt, 30L, TimeUnit.MINUTES);

            return EntityResult.success(map);
        }

        //密码错误
        return null;
    }

    /**
     * 注册
     *
     * @param dtoDoctorRegister
     * @param request
     * @return
     */
    @Override
    public EntityResult register(DtoDoctorRegister dtoDoctorRegister, HttpServletRequest request) {

        //校验邮箱格式
        if (!EmailUtils.isValidQQEmail(dtoDoctorRegister.getEmail())) {
            return EntityResult.error("请输入正确的邮箱");
        }

        //查询当前用户名是否已经存在
        //查询该用户是否存在
        LambdaQueryWrapper<EntityDoctor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EntityDoctor::getUsername, dtoDoctorRegister.getUsername());
        EntityDoctor user = mapperDoctor.selectOne(queryWrapper);
        //存在,抛出异常
        if (!Objects.isNull(user)) {
            return EntityResult.error("当前用户名已经存在");
        }

        //不存在,通过验证码注册
        String loginEmail = dtoDoctorRegister.getEmail();
        String loginCode = dtoDoctorRegister.getEmailCode();
        log.info("loginCode:{}", loginCode);
        //从redis中获取验证码
        String code = stringRedisTemplate.opsForValue().get("login:code:" + loginEmail);
        log.info("code:{}", code);

        //判断
        if (code == null) return EntityResult.error("验证码失效");
        if (!code.equals(loginCode)) return EntityResult.error("验证码错误");

        //验证码正确,接下来就可已注册用户
        EntityDoctor entityDoctor = new EntityDoctor();
        BeanUtils.copyProperties(dtoDoctorRegister, entityDoctor);
        //加密
        entityDoctor.setPassword(Md5Util.getMD5String(dtoDoctorRegister.getPassword()));

        //存入数据库
        mapperDoctor.insert(entityDoctor);

        return EntityResult.success("注册成功");
    }

}
