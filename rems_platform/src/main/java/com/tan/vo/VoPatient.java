package com.tan.vo;

import lombok.Data;

import java.time.LocalDate;

/**
 * Created by TanLiangJie
 * Time:2024/5/20 下午2:40
 */
@Data
public class VoPatient {
    /**
     * id
     */
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
     * 设备状态
     */
    private Integer status; // 是否删除
}
