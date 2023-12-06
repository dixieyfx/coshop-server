package com.coshop.gateway.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author dixiey
 * @version 1.0
 * @description: AuthorizationManager ，鉴权管理类
 * @date 2023/11/29 23:49
 */
@Slf4j
@Component
public class AuthorizationManager  implements ReactiveAuthorizationManager<AuthorizationContext> {

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext authorizationContext) {
        return authentication.map(auth -> {
            //SecurityUserDetails userSecurity = (SecurityUserDetails) auth.getPrincipal();
            String path=authorizationContext.getExchange().getRequest().getURI().getPath();
            for (GrantedAuthority authority : auth.getAuthorities()){
                if (authority.getAuthority().equals("ROLE_USER")&&path.contains("/")) {
                    return new AuthorizationDecision(true);
                } else if (authority.getAuthority().equals("ROLE_ADMIN")&&path.contains("/")) {
                    return new AuthorizationDecision(true);
                }
                //对客户端访问路径与用户角色进行匹配
            }
            return new AuthorizationDecision(false);
        }).defaultIfEmpty(new AuthorizationDecision(false));
    }



}
