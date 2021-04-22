package com.school.master.admin;

import com.school.master.admin.service.UmsAdminCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.lang.reflect.Field;

@SpringBootTest
public class YmlTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private UmsAdminCacheService umsAdminCacheService;

    @Test
    public void ymlTest() throws NoSuchFieldException, IllegalAccessException {
        Field redis_database = umsAdminCacheService.getClass().getDeclaredField("REDIS_DATABASE");
        redis_database.setAccessible(true);

        System.out.println(redis_database.get(umsAdminCacheService));
    }

}
