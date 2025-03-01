package com.socket.grim.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class CorsConfig {

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration configuration = new CorsConfiguration();

    configuration.setAllowCredentials(true);

    configuration.addAllowedOrigin("http://localhost:3000");
    configuration.addAllowedOrigin("http://localhost:5173");
    configuration.addAllowedOriginPattern("https://*.grimeet.com");

    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    configuration.addExposedHeader("Authorization");
    configuration.addAllowedHeader("*");

    source.registerCorsConfiguration("/**", configuration);

    return source;
  }
}
