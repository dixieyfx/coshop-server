package com.coshop.core.common.config;

import com.coshop.core.common.interceptor.LogInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author dixiey
 * @version 1.0
 * @description: TODO
 * @date 2023/11/29 20:00
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public LogInterceptor logInterceptor() {
        return new LogInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * 注册自定义的显示 ResponseResult 注解的拦截器
         */
        registry.addInterceptor(logInterceptor())
                // 拦截配置
               // .addPathPatterns("/**")
                // 排除配置
               // .excludePathPatterns("/error", "/user/login");
                .excludePathPatterns("/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");

        /** 配置knife4j 显示文档 */
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        /**
         * 配置swagger-ui显示文档
         */
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        /** 公共部分内容 */
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }



}
