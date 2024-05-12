package com.tan.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by TanLiangJie
 * Time:2024/5/3 下午1:51
 *
 * 分页查询实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntityPageBean<T> {

    /**
     * 列表总数
     */
    private Long total;
    /**
     * 数据
     */
    private List<T> raws;

}
