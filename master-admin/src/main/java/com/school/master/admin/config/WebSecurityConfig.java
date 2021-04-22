package com.school.master.admin.config;

import com.school.master.admin.dto.UmsAccessPower;
import com.school.master.admin.service.UmsAccessPowerService;
import com.school.master.admin.service.UmsAdminService;
import com.school.master.security.componet.DynamicSecurityService;
import com.school.master.security.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @FileName: SecurityConfig
 * @Author: LeeDream
 * @Date: 2020/12/26 19:41
 * @Description: 对SpringSecurity的配置的扩展，支持自定义白名单资源路径和查询用户逻辑
 * @Version 1.0.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends SecurityConfig {

    @Autowired
    private UmsAdminService umsAdminService;
    @Autowired
    private UmsAccessPowerService umsAccessPowerService;


    /**
     * 查询service获取对象
     *
     * @return
     */
    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        //lamaba表达式实现接口，函数式接口
        //单个方法单个参数时可舍去类型
        //如:(String username) ->  umsAdminService.loadUserByUsername(username),底层也是匿名类实现;
        return username -> umsAdminService.loadUserByUsername(username);
    }

    /**
     * 动态权限
     *
     * @return
     */

    @Bean
    public DynamicSecurityService dynamicSecurityService() {
        //lamaba函数式表达
        return () -> {
            //采用线程安全Map类
            Map<String, ConfigAttribute> map = new ConcurrentHashMap<>(10);
            List<UmsAccessPower> umsAccessPowers = umsAccessPowerService.queryAll();
            for (UmsAccessPower umsAccessPower : umsAccessPowers) {
                //添加uri和权限
                map.put(umsAccessPower.getUri(), new org.springframework.security.access.SecurityConfig(String.valueOf(umsAccessPower.getType())));
            }
            return map;
        };
    }

}



