package com.tan.dto;

import lombok.Data;

/**
 * Created by TanLiangJie
 * Time:2024/5/15 下午1:01
 *
 *
 *
 * 查询病人的DTO
 */

@Data
public class DtoPatientQuery {

    /**
     * 姓名
     */
    private String name;


    /**
     * 类型
     */
    private String category;

    /**
     * 医生id
     */
    private Integer doctorId;

    /**
     * 当前页
     */
    private int currentPage;

    /**
     * 每页记录数
     */
    private int pageSize;

}
