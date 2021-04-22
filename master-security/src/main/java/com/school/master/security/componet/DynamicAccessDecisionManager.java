package com.school.master.security.componet;

import cn.hutool.core.collection.CollUtil;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Iterator;

/**
 * @FileName: DynamicAccessDecisionManager
 * @Author: LeeDream
 * @Date: 2020/12/26 19:36
 * @Description: 动态权限决策管理器，用于判断用户是否有访问权限
 * @Version 1.0.0
 */
public class DynamicAccessDecisionManager implements AccessDecisionManager {
    //被DynamicSecurityFilter验证

    /**
     * @param authentication   用户登录权限类
     * @param object           由super.beforeInvocation(fi),将FilterInvocation参数传入验证
     * @param configAttributes AdminUserDetails中用户权限信息
     * @throws AccessDeniedException               被RestfulAccessDeniedHandler类捕获然后处理handle方法处理
     * @throws InsufficientAuthenticationException
     */

    @Override
    public void decide(Authentication authentication, Object object,
                       Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        // configAttributes从 DynamicSecurityMetadataSource.getAttributes()方法获得
        //当接口未被配置资源权限时直接放行
        if (CollUtil.isEmpty(configAttributes)) {
            return;
        }

        //将访问所需资源权限和用户拥有资源进行比对
        Iterator<ConfigAttribute> iterator = configAttributes.iterator();
        //资源权限
        while (iterator.hasNext()) {
            //获取接口信息权限集合信息
            ConfigAttribute configAttribute = iterator.next();
            String needAuthority = configAttribute.getAttribute();
            //遍历用户权限
            for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
                //删除头尾空格后判断
                //getID()+":"+getType()
                if (needAuthority.trim().equals(grantedAuthority.getAuthority())) {
                    return;
                }
            }
        }

        throw new AccessDeniedException("抱歉，您没有访问权限");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

}
