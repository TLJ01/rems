package com.tan.constant;

/**
 * Created by TanLiangJie
 * Time:2024/5/8 下午8:12
 */
public class RedisConstants {

    /**
     * 注册
     * 验证码code存入redis的key的前缀  ->LOGIN_CODE_KEY+code作为key
     */
    public static final String REGISTER_CODE_KEY = "login:code:";

    /**
     * 登录成功后,LOGIN_DOCTOR_KEY+jwt-->作为key存入redis
     */
    public static final String LOGIN_DOCTOR_KEY = "login:doctor:";


}
