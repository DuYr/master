package com.school.master.security.componet;

import org.springframework.security.access.ConfigAttribute;

import java.util.Map;

/**
 * @FileName: DynamicSecurityService
 * @Author: LeeDream
 * @Date: 2020/12/26 18:08
 * @Description: 动态权限相关业务类
 * @Version 1.0.0
 */
public interface DynamicSecurityService {
    /**
     * 加载资源ANT通配符和资源对应MAP
     */
    Map<String, ConfigAttribute> loadDataSource();
}
