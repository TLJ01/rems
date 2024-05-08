package com.tan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Created by TanLiangJie
 * Time:2024/5/3 下午1:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("patient_info")
public class EntityPatient {

    @TableId(type = IdType.AUTO)
    private Integer id; // 患者ID
    private String name; // 患者姓名
    private String gender; // 性别
    private Integer age; // 年龄
    private Double height; // 身高
    private Double weight; // 体重
    private String phone; // 电话
    private String category; // 类别
    private LocalDate recoveryTime; // 康复时间
    /**
     * 0代表未删除,1代表已删除
     */
    private Integer isDeleted; // 是否删除

}
