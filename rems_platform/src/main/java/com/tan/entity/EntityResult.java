package com.tan.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntityResult {
    private Integer code;//响应码，200 代表成功; 201 代表失败
    private String msg;  //响应信息 描述字符串
    private Object data; //返回的数据

    //增删改 成功响应
    public static EntityResult success(){
        return new EntityResult(200,"success",null);
    }
    //查询 成功响应
    public static EntityResult success(Object data){
        return new EntityResult(200,"success",data);
    }
    //失败响应
    public static EntityResult error(String msg){
        return new EntityResult(201,msg,null);
    }
}