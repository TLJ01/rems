package com.tan.controller;

import com.tan.entity.EntityResult;
import org.springframework.web.bind.annotation.*;

/**
 * Created by TanLiangJie
 * Time:2024/5/27 下午7:08
 */
@RestController
@CrossOrigin
public class ControllerHello {

    @GetMapping("/hello")
    public EntityResult hello() {
        return EntityResult.success("hello");
    }

}
