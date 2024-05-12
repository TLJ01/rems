package com.tan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Created by TanLiangJie
 * Time:2024/5/3 下午3:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoPatientUpdate {

    /**
     * id
     */
    private Integer id;
    /**
     *姓名
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
     * 体重
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

}
