package com.school.master.upload.config;

import com.school.master.common.config.FtpClientConfig;
import com.school.master.common.net.FtpConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import java.security.Principal;
import java.security.PublicKey;

@Configuration
public class BeanConfig {
    @Bean
    public FtpClientConfig ftpConfig() {
        return new FtpClientConfig();
    }

    @Bean
    public FtpConnectionPool ftpPool() {
        return FtpConnectionPool.createPool(ftpConfig());
    }

}
