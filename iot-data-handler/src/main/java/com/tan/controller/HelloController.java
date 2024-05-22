package com.tan.controller;

import com.tan.entity.EntityResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by TanLiangJie
 * Time:2024/5/22 上午10:26
 */
@RestController
@Slf4j
public class HelloController {

    @GetMapping("/hello")
    public EntityResult hello(){
        log.info("hello");
        return EntityResult.success("hello");
    }

}
