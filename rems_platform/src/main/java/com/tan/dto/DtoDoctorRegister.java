package com.tan.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * Created by TanLiangJie
 * Time:2024/5/8 上午9:25
 */
@Data
public class DtoDoctorRegister {

    private String username; // 医生登录用户名
    private String name; // 医生姓名
    private String phone; // 电话
    private String password; // 登录密码
    private String email;//邮箱
    private String emailCode;//邮箱验证码

}
