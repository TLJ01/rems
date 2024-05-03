package com.tan.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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

    private Integer id; // 患者ID
    private String name; // 患者姓名
    private String gender; // 性别
    private Integer age; // 年龄
    private Double height; // 身高
    private Double weight; // 体重
    private String phone; // 电话
    private String category; // 类别
    private LocalDate recoveryTime; // 康复时间

}
