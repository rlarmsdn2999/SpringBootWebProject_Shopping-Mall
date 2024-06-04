package org.zerock.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()); // Disable CSRF protection

        http.authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll() // Allow all other requests without authentication
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/signin") // Redirect to this login page if authentication is needed
                        .loginProcessingUrl("/signin") // Process the login at this URL
                        .defaultSuccessUrl("/main") // Redirect to this URL on successful login
                );

        return http.build();
    }
}
