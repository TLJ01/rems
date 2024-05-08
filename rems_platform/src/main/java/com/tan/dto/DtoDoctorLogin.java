package com.tan.dto;

/**
 * Created by TanLiangJie
 * Time:2024/5/8 上午8:48
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录实体
 */
@Data
public class DtoDoctorLogin {
    private String username;
    private String password;
}
