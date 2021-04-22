package com.school.master.security.componet;


import cn.hutool.core.util.URLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * @FileName: DynamicSecurityMetadataSource
 * @Author: LeeDream
 * @Date: 2020/12/26 19:37
 * @Description: 动态权限数据源，用于获取动态权限规则
 * @Version 1.0.0
 */
public class DynamicSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired(required = false)
    private DynamicSecurityService dynamicSecurityService;
    private static Map<String, ConfigAttribute> configAttributeMap = null;

    @PostConstruct
    public void loadDataSource() {
        configAttributeMap = dynamicSecurityService.loadDataSource();
    }

    public void clearDataSource() {
        configAttributeMap.clear();
        configAttributeMap = null;
    }

    /**
     * Collection<ConfigAttribute> attributes = this.obtainSecurityMetadataSource().getAttributes(object);中被获取
     * object 参数类型为FilterInvocation
     *
     * @param fi
     * @return
     * @throws IllegalArgumentException
     */

    @Override
    public Collection<ConfigAttribute> getAttributes(Object fi) throws IllegalArgumentException {
        if (configAttributeMap == null) this.loadDataSource();
        List<ConfigAttribute> configAttributes = new ArrayList<>();
        //获取当前访问的路径
        String url = ((FilterInvocation) fi).getRequestUrl();
        //获取URI
        String path = URLUtil.getPath(StringUtils.trimAllWhitespace(url));
        PathMatcher pathMatcher = new AntPathMatcher();
        Iterator<String> iterator = configAttributeMap.keySet().iterator();
        //获取访问该路径所需资源
        while (iterator.hasNext()) {
            String pattern = iterator.next();
            //正则判断路径是否匹配
            if (pathMatcher.match(pattern, path)) {
                //匹配成功则从集合获取访问该uri所需权限，uri为key
                configAttributes.add(configAttributeMap.get(pattern));
            }
        }
        // 未设置操作请求权限，返回空集合
        return configAttributes;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

}
