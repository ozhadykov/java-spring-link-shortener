package com.link_shortener.link_shortener.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Allow anyone to access the home page
                        .requestMatchers("/").permitAll()
                        // Any other request must be authenticated
                        .anyRequest().authenticated()
                )
                // This one line enables the entire session/cookie login flow!
                .formLogin(withDefaults());

        return http.build();
    }

}
