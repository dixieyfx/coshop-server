package com.coshop.gateway.config;

import com.coshop.gateway.security.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.DelegatingReactiveAuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;

import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

import java.util.LinkedList;

/**
 * @author dixiey
 * @version 1.0
 * @description: TODO
 * @date 2023/11/29 23:56
 */
@EnableWebFluxSecurity
@Configuration
@Slf4j
public class WebSecurityConfig {

    @Autowired
    SecurityUserDetailsService securityUserDetailsService;
    @Autowired
    AuthorizationManager authorizationManager;
    @Autowired
    AccessDeniedHandler accessDeniedHandler;
    @Autowired
    AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    AuthenticationFaillHandler authenticationFaillHandler;
    @Autowired
    SecurityRepository securityRepository;
    @Autowired
    CookieToHeadersFilter cookieToHeadersFilter;
    @Autowired
    LogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    LogoutHandler logoutHandler;

    @Autowired
    AuthenticationEntryPoint authenticationEntryPoint;
    private final String[] path={
            "/favicon.ico",
            "/book/**",
            "/user/login.html",
            "/user/__MACOSX/**",
            "/user/css/**",
            "/user/fonts/**",
            "/user/images/**",
            "/user/login"};
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.addFilterBefore(cookieToHeadersFilter, SecurityWebFiltersOrder.HTTP_HEADERS_WRITER);
        //SecurityWebFiltersOrder枚举类定义了执行次序
        // 请求拦截处理
        http.authorizeExchange(exchange -> exchange
                        .pathMatchers(path).permitAll()
                        .pathMatchers(HttpMethod.OPTIONS)
                        .permitAll()
                        .anyExchange().access(authorizationManager)//权限

                //.and().authorizeExchange().pathMatchers("/user/normal/**").hasRole("ROLE_USER")
                //.and().authorizeExchange().pathMatchers("/user/admin/**").hasRole("ROLE_ADMIN")
                //也可以这样写 将匹配路径和角色权限写在一起
        )

                .httpBasic()
                .and()
                //登录接口
                .formLogin().loginPage("/user/login")
                //认证成功
                .authenticationSuccessHandler(authenticationSuccessHandler)
                //登陆验证失败
                .authenticationFailureHandler(authenticationFaillHandler)
                .and()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
                //基于http的接口请求鉴权失败
                .accessDeniedHandler(accessDeniedHandler)
                //必须支持跨域
                .and().csrf().disable()

                //不通过Session获取SecurityContext
                .logout().logoutUrl("/user/logout")
                .logoutHandler(logoutHandler)
                .logoutSuccessHandler(logoutSuccessHandler);
        http.securityContextRepository(securityRepository);
        //http.securityContextRepository(NoOpServerSecurityContextRepository.getInstance());//无状态 默认情况下使用的WebSession
        return http.build();
    }

    @Bean
    public ReactiveAuthenticationManager reactiveAuthenticationManager() {
        LinkedList<ReactiveAuthenticationManager> managers = new LinkedList<>();
        managers.add(authentication -> {
            // 其他登陆方式
            return Mono.empty();
        });
        managers.add(new UserDetailsRepositoryReactiveAuthenticationManager(securityUserDetailsService));
        return new DelegatingReactiveAuthenticationManager(managers);
    }






}
