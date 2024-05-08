package com.tan;

import com.tan.entity.EntityDoctor;
import com.tan.utils.EmailUtils;
import com.tan.utils.Md5Util;
import com.tan.utils.RedisCache;
import com.tan.utils.UserThreadLocal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RemsPlatformApplicationTests {

    @Autowired
    private RedisCache redisCache;

    @Test
    void contextLoads() {
        System.out.println(Md5Util.getMD5String("123456"));
    }

    @Test
    void TestThreadLoaal(){
        EntityDoctor entityDoctor = new EntityDoctor();
        entityDoctor.setId(1L);
        entityDoctor.setName("always");
        entityDoctor.setUsername("tantan");
        UserThreadLocal.put(entityDoctor);
        System.out.println(UserThreadLocal.get());
    }

    @Test
    void TestRedis() {
        redisCache.setCacheObject("tan", "red");

    }

    @Test
    void TestEmail(){
        System.out.println(EmailUtils.isValidQQEmail("2914421833@qq.com"));
        System.out.println(EmailUtils.isValidQQEmail("2914421833@qq.om"));
        System.out.println(EmailUtils.isValidQQEmail("291442183311@qq.com"));
        System.out.println(EmailUtils.isValidQQEmail("29144218ss1@qq.com"));
    }
}
