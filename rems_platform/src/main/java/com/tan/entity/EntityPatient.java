package com.tan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Created by TanLiangJie
 * Time:2024/5/3 下午1:29
 *
 * 病人实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("patient_info")
public class EntityPatient {

    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private String gender;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 身高
     */
    private Double height;
    /**
     * 体重
     */
    private Double weight;
    /**
     * 电话
     */
    private String phone;
    /**
     * 类别
     */
    private String category;
    /**
     * 康复时间
     */
    private LocalDate recoveryTime;

    /**
     * 医生id
     */
    private Integer doctorId;
    /**
     * 0代表未删除,1代表已删除
     */
    private Integer isDeleted; // 是否删除

}
