package com.tan.dto;
import lombok.Data;
/**
 * Created by TanLiangJie
 * Time:2024/5/8 上午8:48
 */

/**
 * 登录实体
 */
@Data
public class DtoDoctorLogin {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
}
