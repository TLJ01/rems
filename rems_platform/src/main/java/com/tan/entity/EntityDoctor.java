package com.tan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by TanLiangJie
 * Time:2024/5/3 下午2:48
 *
 * 医生实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("doctor_info")
public class EntityDoctor implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id; // 医生ID
    private String username; // 医生登录用户名
    private String name; // 医生姓名
    private String phone; // 电话
    private String password; // 登录密码
}
