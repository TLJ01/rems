package com.tan.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tan.constant.EntityResponseConstants;
import com.tan.dto.DtoDoctorLogin;
import com.tan.dto.DtoDoctorRegister;
import com.tan.entity.EntityResult;
import com.tan.mapper.MapperDoctor;
import com.tan.entity.EntityDoctor;
import com.tan.entity.EntityPageBean;
import com.tan.service.ServiceDoctor;
import com.tan.utils.*;
import jakarta.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.tan.constant.RedisConstants.*;

/**
 * Created by TanLiangJie
 * Time:2024/5/3 下午2:46
 *
 *
 * 医生服务层
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
     * @param
     */
//    @Override
//    public EntityResult login(DtoDoctorLogin dtoDoctorLogin) {
//        String username = dtoDoctorLogin.getUsername();
//        //查询该用户是否存在
//        LambdaQueryWrapper<EntityDoctor> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(EntityDoctor::getUsername, username);
//        EntityDoctor user = mapperDoctor.selectOne(queryWrapper);
//
//        //不存在,抛出异常
//        if (Objects.isNull(user)) {
//            return EntityResult.error(EntityResponseConstants.USER_NOT_FOUND);
//        }
//
//
//        //存在,校验密码
//        if (user.getPassword().equals(Md5Util.getMD5String(dtoDoctorLogin.getPassword()))) {
//            //用户名和密码正确,生成jwt
//            Map<String, Object> claims = new HashMap<>();
//            claims.put("username", username);
//            claims.put("id", user.getId());
//            String jwt = JwtUtils.generateJwt(claims);
//            HashMap<String, String> map = new HashMap<>();
//            map.put("token", jwt);
//
//            //将用户信息存入redis;token作为key,用户信息作为value
//            //将UserDto转为map-->这种方式可以避免mybatis类型转换错误
//            //StringRedisTemplate中的key,value都是字符串
//            Map<String, Object> userMap = BeanUtil.beanToMap(user, new HashMap<>(),
//                    CopyOptions.create()
//                            .setIgnoreNullValue(true)
//                            .setFieldValueEditor((fieldName, fieldValue) -> fieldValue.toString()));
//
//            stringRedisTemplate.opsForHash().putAll(LOGIN_DOCTOR_KEY + jwt, userMap);
//            stringRedisTemplate.expire(LOGIN_DOCTOR_KEY + jwt, 30L, TimeUnit.MINUTES);
//
//
//            log.info("map:{}",map);
//
//            return EntityResult.success(map);
//        }

    @Override
    public EntityResult login(String username, String password) {
        //String username = dtoDoctorLogin.getUsername();
        //查询该用户是否存在
        LambdaQueryWrapper<EntityDoctor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EntityDoctor::getUsername, username);
        EntityDoctor user = mapperDoctor.selectOne(queryWrapper);

        //不存在,抛出异常
        if (Objects.isNull(user)) {
            return EntityResult.error(EntityResponseConstants.USER_NOT_FOUND);
        }


        //存在,校验密码
        if (user.getPassword().equals(Md5Util.getMD5String(password)) ){
            //用户名和密码正确,生成jwt
            Map<String, Object> claims = new HashMap<>();
            claims.put("username", username);
            claims.put("id", user.getId());
            String jwt = JwtUtils.generateJwt(claims);
            HashMap<String, String> map = new HashMap<>();
            map.put("token", jwt);

            //将用户信息存入redis;token作为key,用户信息作为value
            //将UserDto转为map-->这种方式可以避免mybatis类型转换错误
            //StringRedisTemplate中的key,value都是字符串
            Map<String, Object> userMap = BeanUtil.beanToMap(user, new HashMap<>(),
                    CopyOptions.create()
                            .setIgnoreNullValue(true)
                            .setFieldValueEditor((fieldName, fieldValue) -> fieldValue.toString()));

            stringRedisTemplate.opsForHash().putAll(LOGIN_DOCTOR_KEY + jwt, userMap);
            stringRedisTemplate.expire(LOGIN_DOCTOR_KEY + jwt, 30L, TimeUnit.MINUTES);


            log.info("map:{}",map);

            return EntityResult.success(map);
        }
        //密码错误
        return EntityResult.error(EntityResponseConstants.PASSWORD_INCORRECT);
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
            return EntityResult.error(EntityResponseConstants.EMAIL_INVALID);
        }

        //查询当前用户名是否已经存在
        //查询该用户是否存在
        LambdaQueryWrapper<EntityDoctor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EntityDoctor::getUsername, dtoDoctorRegister.getUsername());
        EntityDoctor user = mapperDoctor.selectOne(queryWrapper);
        //存在,抛出异常
        if (!Objects.isNull(user)) {
            return EntityResult.error(EntityResponseConstants.USER_ISEXIST);
        }

        //不存在,通过验证码注册
        String loginEmail = dtoDoctorRegister.getEmail();
        String loginCode = dtoDoctorRegister.getEmailCode();
        //从redis中获取验证码
        String code = stringRedisTemplate.opsForValue().get(REGISTER_CODE_KEY + loginEmail);

        //判断
        if (code == null) return EntityResult.error(EntityResponseConstants.CODE_EXPIRED);
        if (!code.equals(loginCode)) return EntityResult.error(EntityResponseConstants.CODE_INCORRECT);

        //验证码正确,接下来就可已注册用户
        EntityDoctor entityDoctor = new EntityDoctor();
        BeanUtils.copyProperties(dtoDoctorRegister, entityDoctor);
        //加密
        entityDoctor.setPassword(Md5Util.getMD5String(dtoDoctorRegister.getPassword()));

        //存入数据库
        mapperDoctor.insert(entityDoctor);

        return EntityResult.success(EntityResponseConstants.REGISTER_SUCCESS);
    }


    /**
     * 退出登录
     * @return
     */
    @Override
    public EntityResult logout(HttpServletRequest request) {
        //获取token
        String token = request.getHeader("Authorization");
        //删除redis中的用户信息
        //stringRedisTemplate.opsForHash().delete(LOGIN_DOCTOR_KEY+token);

        //getRedisTemplate().opsForHash().delete(id.toString());这个方法删除的是Hash中的键值对，并不是整个Hash


        stringRedisTemplate.delete(LOGIN_DOCTOR_KEY + token);

        //System.out.println(UserThreadLocal.get());
        //此时应同时删除UserThreadLocal中的数据
        UserThreadLocal.remove();

        return EntityResult.success(EntityResponseConstants.LOGOUT_SUCCESS);
    }


}
