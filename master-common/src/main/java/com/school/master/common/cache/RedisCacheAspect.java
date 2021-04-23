package com.school.master.common.cache;

import com.school.master.common.annotation.RedisCache;
import com.school.master.common.service.RedisService;
import com.school.master.common.utils.PasswordEnCodeUtil;
import org.apache.poi.ss.formula.functions.T;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * @FileName: RedisCacheAspect
 * @Author: LeeDream
 * @Date: 2021/3/24 18:48
 * @Description:
 * @Version 1.0.0
 */

@Aspect
@Component
@Order(2)
public class RedisCacheAspect {
    @Autowired
    private RedisService redisService;
    @Autowired
    private PasswordEnCodeUtil passwordEnCodeUtil;

    @Around("@annotation(redisCache)")
    public Object addObjectCache(ProceedingJoinPoint joinPoint, RedisCache redisCache) {
        //获得注解描述
        String value = redisCache.desc();
        Signature signature = joinPoint.getSignature();
        Class returnType = getMethodReturnType(signature);

        Object proceed = null;
        try {

        } catch (Throwable throwable) {
            return null;
        }
        return proceed;
    }

    private Class getMethodReturnType(Signature signature) {
        MethodSignature methodSignature = (MethodSignature) signature;
        return methodSignature.getReturnType();

    }

    /**
     * @param t
     * @return 通过toString方法获得key, 全类名+MD5
     */

    private String getMd5Key(T t) {
        String md5Key = passwordEnCodeUtil.passwordMd5Handle(t.toString());
        return md5Key + ":" + t.getClass();
    }


}
