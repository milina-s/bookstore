package com.example.bookstore.security;

import com.example.bookstore.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> userRepository
                .findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("No such user"));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/api/v1/bookstore/**").permitAll();
                    auth.requestMatchers("/api/v1/bookstore/user/**").hasAuthority("USER");

                    auth.requestMatchers("/api/v1/bookstore/admin/**",
                                    "/api/v1/bookstore/categories/update",
                                    "/api/v1/bookstore/categories/save")
                            .hasAuthority("ADMIN");

                    auth.requestMatchers("/api/v1/bookstore/manager/**").hasAuthority("MANAGER");
//                    auth.requestMatchers("/api/v1/security/user").hasAuthority("USER");
//                    auth.requestMatchers("/api/v1/security/admin").hasAuthority("ADMIN");
//                    auth.anyRequest().authenticated();
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .build();
    }

}