package com.example.signalbackend.global.security;

import com.example.signalbackend.global.error.GlobalExceptionFilter;
import com.example.signalbackend.global.security.jwt.JwtTokenFilter;
import com.example.signalbackend.global.security.jwt.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class FilterConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
        private final ObjectMapper objectMapper;
        private final JwtTokenProvider jwtTokenProvider;

        @Override
        public void configure(HttpSecurity builder) {
                builder.addFilterBefore(
                        new JwtTokenFilter(jwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class
                );

                builder.addFilterBefore(
                        new GlobalExceptionFilter(objectMapper),
                        JwtTokenFilter.class
                );
        }
}
