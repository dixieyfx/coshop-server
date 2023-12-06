package com.coshop.gateway.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author dixiey
 * @version 1.0
 * @description: TODO
 * @date 2023/11/29 23:35
 */
@Component("securityUserDetailsService")
@Slf4j
public class SecurityUserDetailsService implements ReactiveUserDetailsService {

    private final PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
    @Override
    public Mono<UserDetails> findByUsername(String username) {
        //调用数据库根据用户名获取用户
        log.info("username:"+username);
        if(!username.equals("admin")&&!username.equals("user")) {
            throw new UsernameNotFoundException("username error");
        } else {
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            if (username.equals("admin")) {
                //ROLE_ADMIN
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            }
            if (username.equals("user")) {
                //ROLE_ADMIN
                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            }
            SecurityUserDetails securityUserDetails = new SecurityUserDetails(username,"{bcrypt}"+passwordEncoder.encode("123"),authorities,1L);
            return Mono.just(securityUserDetails);
        }

    }



}
