package com.iclass.login.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Slf4j
@EnableMethodSecurity(prePostEnabled = true)

public class SecurityConfig {
    private static final String[] PERMIT_LIST = {
            "/", "/signup", "/bulletin-board", "/login", "/js/**", "/api/**"
    };

    @Bean
    public SecurityFilterChain filterchain(HttpSecurity http) throws Exception {
        http.csrf(c -> c.disable()).authorizeHttpRequests(
                r -> r

                        .requestMatchers(PERMIT_LIST)
                        .permitAll()
                        .anyRequest()
                        .authenticated()
        ).formLogin(form -> form.loginPage("/login")
                .defaultSuccessUrl("/")
                .permitAll());
        return http.build();
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

}
