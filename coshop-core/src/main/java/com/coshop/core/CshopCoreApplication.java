package com.coshop.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author dixiey
 * @version 1.0
 * @description:
 * @date 2023/11/25 23:18
 */

@ConditionalOnMissingBean(WebMvcConfigurationSupport.class)
@SpringBootApplication()
@EnableWebMvc
@MapperScan("com.coshop.core.*.mapper")
public class CshopCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(CshopCoreApplication.class,args);
    }

}
