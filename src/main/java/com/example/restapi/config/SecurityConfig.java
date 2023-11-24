package com.example.restapi.config;

import com.example.restapi.jwt.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
                .csrf((csrf) -> csrf
                        .ignoringRequestMatchers("/no-csrf"))
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
                                .requestMatchers("/api/auth/token").permitAll()
                                .requestMatchers("/api/customers/**").permitAll() // Ensure explicit permission for customers
                        // .requestMatchers("/api/**").hasAuthority("USER")
                         .anyRequest().authenticated()
                )
       .addFilterAfter(jwtFilter, AuthenticationFilter.class).build();

    }
}
