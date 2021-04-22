package com.school.master.admin.config;

import com.school.master.common.config.BaseRedidsConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class RedisConfig extends BaseRedidsConfig {

}
