package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // 這裡改成所有路徑都不需要認證
                        .anyRequest().permitAll()
                )
                // 關閉 Basic
                .httpBasic(httpBasic -> httpBasic.disable())
                // 關閉 Form Login
                .formLogin(form -> form.disable());

        return http.build();
    }

}
