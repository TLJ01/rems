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

    private String name; // 患者姓名
    private String gender; // 性别
    private Integer age; // 年龄
    private Double height; // 身高
    private Double weight; // 体重
    private String phone; // 电话
    private String category; // 类别

}
