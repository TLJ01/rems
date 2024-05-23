package com.tan;

import com.tan.dto.DtoPatientQuery;
import com.tan.entity.EntityDoctor;
import com.tan.mapper.MapperPatient;
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

    @Autowired
    private MapperPatient mapperPatient;

    @Test
    void contextLoads() {
        System.out.println(Md5Util.getMD5String("123456"));
    }

    @Test
    void TestThreadLoaal() {
        EntityDoctor entityDoctor = new EntityDoctor();
        entityDoctor.setId(1);
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
    void TestEmail() {
        System.out.println(EmailUtils.isValidQQEmail("2914421833@qq.com"));
        System.out.println(EmailUtils.isValidQQEmail("2914421833@qq.om"));
        System.out.println(EmailUtils.isValidQQEmail("291442183311@qq.com"));
        System.out.println(EmailUtils.isValidQQEmail("29144218ss1@qq.com"));
    }

    @Test
    void pageTest() {

        DtoPatientQuery dtoPatientQuery = new DtoPatientQuery();

        // 设置默认值
        Integer currentPage = 1;
        Integer pageSize = 5;
        String name = null;
        String category = null;

        // 如果 dtoPatientQuery 不为空，则使用传入的参数
        if (dtoPatientQuery != null) {
            currentPage = dtoPatientQuery.getCurrentPage() != 0 ? dtoPatientQuery.getCurrentPage() : currentPage;
            pageSize = dtoPatientQuery.getPageSize() != 0 ? dtoPatientQuery.getPageSize() : pageSize;
            name = dtoPatientQuery.getName();
            category = dtoPatientQuery.getCategory();
        }

//        IPage<EntityPatient> page = new Page<>(currentPage, pageSize);
//        //查询条件,isDeleted=0,未被删除的
//        LambdaQueryWrapper<EntityPatient> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(EntityPatient::getIsDeleted,0);
//
//        //当前医生自己的病人
//        wrapper.eq(EntityPatient::getDoctorId,getDoctorId());
//
//        //模糊查询-->根据名字/病型
//        if (category !=null) wrapper.like(EntityPatient::getCategory, category);
//        if (name !=null) wrapper.like(EntityPatient::getName, name);
//
//        mapperPatient.selectPage(page, wrapper);
//        return new EntityPageBean(page.getTotal(),page.getRecords());

//
//        PageHelper.startPage(currentPage, pageSize);
//        Integer doctorId = UserThreadLocal.get().getId();
//        Page<VoPatient> page = mapperPatient.pageQuery(doctorId, category, name);
//        System.out.println(page.getTotal());
    }
}
