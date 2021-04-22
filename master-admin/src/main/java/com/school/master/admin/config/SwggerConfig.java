package com.school.master.admin.config;

import com.school.master.common.config.BaseSwaggerConfig;
import com.school.master.common.config.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwggerConfig extends BaseSwaggerConfig {
    /**
     * 自定义Swagger配置
     */
    @Override
    public SwaggerProperties swaggerProperties() {
        SwaggerProperties swaggerProperties = new SwaggerProperties();
        swaggerProperties.setApiBasePackage("com.school.master.admin.controller");
        swaggerProperties.setTitle("admin业务接口测试");
        swaggerProperties.setDescription("school-master-admin模块相关接口文档");
        swaggerProperties.setContactName("school-master");
        swaggerProperties.setEnableSecurity(true);
        swaggerProperties.setVersion("1.0.0");
        return swaggerProperties;
    }


}
