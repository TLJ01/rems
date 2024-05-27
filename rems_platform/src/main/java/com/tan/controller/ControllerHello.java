package com.tan.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by TanLiangJie
 * Time:2024/5/27 下午7:08
 */
@RestController

public class ControllerHello {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

}