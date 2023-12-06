package com.coshop.gateway.security;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.coshop.gateway.util.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.logout.ServerLogoutHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @author dixiey
 * @version 1.0
 * @description: LogoutHandler，LogoutSuccessHandler 登出处理类
 * @date 2023/11/29 23:51
 */

@Component
@Slf4j
public class LogoutHandler implements ServerLogoutHandler {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Override
    public Mono<Void> logout(WebFilterExchange webFilterExchange, Authentication authentication) {
        HttpCookie cookie=webFilterExchange.getExchange().getRequest().getCookies().getFirst("token");
        try {
            if (cookie != null) {
                Map<String,Object> userMap= JWTUtils.getTokenInfo(cookie.getValue());
                redisTemplate.delete((String) userMap.get("username"));
            }
        }catch (JWTDecodeException e) {
            return Mono.error(e);
        }

        return Mono.empty();
    }






}
