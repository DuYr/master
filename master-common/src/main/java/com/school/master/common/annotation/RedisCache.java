package com.school.master.common.annotation;

import java.lang.annotation.*;

/**
 * @FileName: RedisCache
 * @Author: LeeDream
 * @Date: 2021/2/6 16:35
 * @Description: 在需要进行缓存的方法上使用注解
 * @Version 1.0.0
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisCache {
    /**
     * redis key
     *
     * @return
     */

    String key() default "MD5";


    /**
     * 缓存说明
     *
     * @return
     */

    String desc() default "";
}
