package com.udg.my.routine.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {

  // Rule cors
  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration corsConfiguration = new CorsConfiguration();
    corsConfiguration.setAllowedOrigins(Arrays.asList(
      "http://localhost:4200",
      "http://localhost:8080",
      "https://udg.netlify.app",
      "https://myroutine.up.railway.app"));
    corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE"));
    corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
    corsConfiguration.setExposedHeaders(Arrays.asList("Authorization")); // Exponer el encabezado de autorización

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", corsConfiguration);

    return source;
  }
  
}
