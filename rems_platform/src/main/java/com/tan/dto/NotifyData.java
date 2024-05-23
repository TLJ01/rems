package com.tan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by TanLiangJie
 * Time:2024/5/22 下午12:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotifyData {
    /**
     * 推送消息header
     */
    private NotifyDataHeader header;
    /**
     * 推送消息body
     */
    private NotifyDataBody body;
}
