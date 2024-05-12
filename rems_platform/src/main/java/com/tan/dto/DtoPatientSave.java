package com.tan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by TanLiangJie
 * Time:2024/5/3 下午3:01
 *
 * 保存患者的信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoPatientSave {

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

}
