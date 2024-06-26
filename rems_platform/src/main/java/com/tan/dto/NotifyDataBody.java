package com.tan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

/**
 * Created by TanLiangJie
 * Time:2024/5/22 下午12:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotifyDataBody {

    /**
     * 设备的服务信息列表。
     */
    private List<DevicePropertyV5> services;

}
