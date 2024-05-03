package com.tan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by TanLiangJie
 * Time:2024/5/3 下午1:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBean<T> {

    private Long total;//总数
    private List<T> raws;//数据

}
