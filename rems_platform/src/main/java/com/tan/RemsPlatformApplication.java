package com.tan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tan.mapper")
public class RemsPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(RemsPlatformApplication.class, args);
    }

}
