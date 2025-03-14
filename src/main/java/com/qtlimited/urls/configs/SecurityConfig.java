package com.qtlimited.urls.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.qtlimited.urls.filters.JWTAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Autowired
        private JWTAuthenticationFilter authenticationFilter;

        @Autowired
        private AuthenticationProvider authenticationProvider;

        @Bean
        SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http.csrf(AbstractHttpConfigurer::disable)
                                .cors(Customizer.withDefaults())
                                .authorizeHttpRequests(
                                                request -> request
                                                                .requestMatchers("/auth/**", "/swagger-ui/**",
                                                                                "/v3/api-docs/**",
                                                                                "/swagger-ui.html", "/static/**")
                                                                .permitAll()
                                                                .anyRequest().authenticated())
                                .sessionManagement(manager -> manager
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authenticationProvider(authenticationProvider).addFilterBefore(
                                                authenticationFilter, UsernamePasswordAuthenticationFilter.class);
                return http.build();
        }
}
