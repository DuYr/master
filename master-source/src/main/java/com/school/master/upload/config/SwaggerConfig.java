package com.school.master.upload.config;

import com.school.master.common.config.BaseSwaggerConfig;
import com.school.master.common.config.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {
    @Override
    public SwaggerProperties swaggerProperties() {
        SwaggerProperties swaggerProperties = new SwaggerProperties();
        swaggerProperties.setApiBasePackage("com.school.master.upload.controller");
        swaggerProperties.setTitle("文件图片上传业务接口测试");
        swaggerProperties.setDescription("school-master-upload接口文档");
        swaggerProperties.setContactName("school-master");
        swaggerProperties.setEnableSecurity(true);
        swaggerProperties.setVersion("1.0.0");
        return swaggerProperties;
    }
}
