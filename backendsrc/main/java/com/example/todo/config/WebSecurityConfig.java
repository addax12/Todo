package com.example.todo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.todo.security.jwtAuthenticationFilter;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@Slf4j
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(AbstractHttpConfigurer::disable)  // CORS 설정 비활성화
            .csrf(AbstractHttpConfigurer::disable)  // CSRF 설정 비활성화
            .httpBasic(AbstractHttpConfigurer::disable)  // HTTP 기본 인증 비활성화
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // 세션 관리 설정
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/", "/auth/**").permitAll()  // 특정 경로에 대한 접근 허용
                .anyRequest().authenticated()  // 나머지 요청에 대해서는 인증 요구
            );

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);  // 커스텀 JWT 필터 추가

        return http.build();
    }

    @Bean
    public jwtAuthenticationFilter jwtAuthenticationFilter() {
        return new jwtAuthenticationFilter();
    }
}
