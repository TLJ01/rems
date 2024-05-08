package com.tan;

import com.tan.utils.Md5Util;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RemsPlatformApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(Md5Util.getMD5String("123456"));
    }

}
