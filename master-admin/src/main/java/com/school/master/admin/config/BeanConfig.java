package com.school.master.admin.config;

import cn.hutool.crypto.digest.MD5;
import com.school.master.common.utils.PasswordEnCodeUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Value("${password.salt}")
    private byte[] salt;

    @Bean
    public MD5 md5() {
        MD5 md5 = new MD5();
        md5.setSalt(salt);
        return md5;
    }

    @Bean
    public PasswordEnCodeUtil passwordEnCodeUtil() {
        return new PasswordEnCodeUtil();
    }

}
