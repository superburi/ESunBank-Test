package com.howard.esunbanktest.config;

import com.howard.esunbanktest.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(@Autowired JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 使用 JWT, 不使用 session
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // 登入的 API 允許匿名訪問
                        .requestMatchers("/auth/login", "/user", "/books").permitAll()
                        // 其它路徑需登入 ( 帶 Token )
                        .anyRequest().authenticated()
                )
                // 關閉 Basic Auth
                .httpBasic(httpBasic -> httpBasic.disable())
                // 關閉 Form Login
                .formLogin(form -> form.disable());

        // 把 JwtAuthenticationFilter 加到 UsernamePasswordAuthenticationFilter 前
        http.addFilterBefore(jwtAuthenticationFilter,
                org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
