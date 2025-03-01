package com.socket.grim.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable()) // CSRF 비활성화 (Stateless 환경)
            .sessionManagement(sessionManagement ->
                    sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션 생성 방지
            .authorizeHttpRequests(authorizeRequests ->
                    authorizeRequests
                            .requestMatchers("/").permitAll()
                            .anyRequest().authenticated()
            )
            .formLogin(withDefaults())
            .rememberMe(Customizer.withDefaults()); // Remember Me 기능 활성화(기본값 사용) -> 쿠키 기반 자동 로그인
//            .exceptionHandling().authenticationEntryPoint({
//                (request, response, authException) -> response.sendRedirect("/login")
//            });
    http.apply(new CustomSecurityConfigurer().setFlag(false));
    return http.build();
  }
}
