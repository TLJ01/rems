package com.tan.vo;

import com.tan.dto.DeviceProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by TanLiangJie
 * Time:2024/5/28 上午10:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoMonitorData {

    /**
     * 患者名字
     */
    private String name;

    /**
     * 检测数据
     */
    private List<DeviceProperties> list;
}
