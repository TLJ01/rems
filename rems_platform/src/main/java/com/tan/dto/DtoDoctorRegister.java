package com.tan.dto;
import lombok.Data;

/**
 * Created by TanLiangJie
 * Time:2024/5/8 上午9:25
 *
 * 注册实体
 */
@Data
public class DtoDoctorRegister {

    /**
     * 用户名
     */
    private String username;
    /**
     * 姓名
     */
    private String name;
    /**
     * 电话
     */
    private String phone;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 验证码
     */
    private String emailCode;

}
