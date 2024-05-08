package com.tan.service.impl;

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
import com.tan.utils.JwtUtils;
import com.tan.utils.Md5Util;
import com.tan.utils.UserThreadLocal;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by TanLiangJie
 * Time:2024/5/3 下午2:46
 */
@Slf4j
@Service
public class ServiceDoctorImpl implements ServiceDoctor {

    @Autowired
    private MapperDoctor mapperDoctor;

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

        log.info("rawPassword:{}", user.getPassword());
        log.info("loginPassword:{}", Md5Util.getMD5String(dtoDoctorLogin.getPassword()));

        //存在,校验密码
        if (user.getPassword().equals(Md5Util.getMD5String(dtoDoctorLogin.getPassword()))) {
            //用户名和密码正确,生成jwt
            Map<String, Object> claims = new HashMap<>();
            claims.put("username", username);
            claims.put("id", user.getId());
            String jwt = JwtUtils.generateJwt(claims);
            HashMap<String, String> map = new HashMap<>();
            map.put("token", jwt);

            //将用户信息存入线程中
            UserThreadLocal.put(user);

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
        String vqq = dtoDoctorRegister.getEmail();
        String vcode = dtoDoctorRegister.getEmailCode();
        HttpSession session = request.getSession();
        //先用的session可以采用security
        String qq = (String) session.getAttribute("qq");
        String code = (String) session.getAttribute("code");
        LocalDateTime expireTime = (LocalDateTime) session.getAttribute("expireTime");
        LocalDateTime currentTime = LocalDateTime.now();
        // 检查验证码是否过期
        if (expireTime != null && currentTime.isBefore(expireTime)) {
            // 验证码有效，执行相应逻辑
            // return "验证码过期了！";
            if (vqq.equals(qq) && vcode.equals(code)) {


                //验证成功,存入数据库
                EntityDoctor entityDoctor = new EntityDoctor();
                BeanUtils.copyProperties(dtoDoctorRegister, entityDoctor);
                entityDoctor.setPassword(Md5Util.getMD5String(dtoDoctorRegister.getPassword()));
                mapperDoctor.insert(entityDoctor);
                return EntityResult.success("注册成功");


            } else {
                return EntityResult.success("注册失败");
            }
        } else {
            // 验证码已过期，执行相应逻辑
            session.removeAttribute("qq");
            session.removeAttribute("code");
            return EntityResult.error("验证码已过期");

        }
    }

}
