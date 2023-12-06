package com.coshop.core.common.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author dixiey
 * @version 1.0
 * @description: //声明该类为配置类  //声明启动Swagger3
 * @date 2023/11/26 13:27
 */

@Configuration
@EnableOpenApi
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                // 是否启用swagger
                .enable(true)
                //用来创建该API的基本信息，展示在文档的页面中（自定义展示的信息）
                .apiInfo(apiInfo())
                // 设置哪些接口暴露给Swagger展示
                .select()
                // 扫描所有有注解的api，用这种方式更灵活
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                // 扫描所有包中的swagger注解
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 设置标题
                .title("咖啡外卖接口文档")
                // 描述
                .description("咖啡外卖接口")
                .contact(new Contact("dixiey", null, null))
                // 作者信息
                .version("v1.0")
                // 版本
                .build();
    }


}
