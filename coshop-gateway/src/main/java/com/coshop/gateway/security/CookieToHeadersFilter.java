package com.coshop.gateway.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;



/**
 * @author dixiey
 * @version 1.0
 * @description: CookieToHeadersFilter ，将Cookie写入Http请求头中
 * @date 2023/11/29 23:53
 */

@Slf4j
@Component
public class CookieToHeadersFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        try {

            HttpCookie cookie=exchange.getRequest().getCookies().getFirst("token");
            if (cookie != null) {
                String token = cookie.getValue();
                ServerHttpRequest request=exchange.getRequest().mutate().header(HttpHeaders.AUTHORIZATION,token).build();
                return chain.filter(exchange.mutate().request(request).build());
            }
        }catch (Exception e) {
            log.error(e.getMessage());
        }

        return chain.filter(exchange);

    }






}
