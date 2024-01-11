package com.pfe.backend.security;



import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthConverter jwtAuthConverter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable().authorizeHttpRequests()
              
            .requestMatchers(
                "/swagger-ui.html",
                "/swagger-ui/**",
                "/swagger-resources/**",
                "/swagger-resources/**",
                "/webjars/**",
                "/v3/api-docs/**",
                "/configuration/security",
                "/configuration/ui"
            ).permitAll()
            .requestMatchers("/documents/**").hasAnyRole("client_entrepreneur") 
            .anyRequest()
              .authenticated(); ;
                
    
        http
            .oauth2ResourceServer()
                .jwt()
                    .jwtAuthenticationConverter(jwtAuthConverter);
    
        http
            .sessionManagement()
                .sessionCreationPolicy(STATELESS);
      /*hedhii li nsitha w kasrtli kraymi */   http.cors() ;
        return http.build();
    }
}