package com.iclass.login.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Slf4j
@EnableMethodSecurity(prePostEnabled = true)

public class SecurityConfig {
    private static final String[] PERMIT_LIST = {
            "/", "/signup", "/login", "/js/**", "/api/**", "/index", "/favicon.ico", "/error"
    };

    @Bean
    public SecurityFilterChain filterchain(HttpSecurity http) throws Exception {
        log.info(":::::: Custom Security Filter Chain 작동중");
        http.csrf(c -> c.disable()).authorizeHttpRequests(
                r -> r
                        .requestMatchers(PERMIT_LIST)
                        .permitAll()
                        .anyRequest()
                        .authenticated()
        )
        .formLogin(form -> form.loginPage("/login")
                .defaultSuccessUrl("/")
                .usernameParameter("email")
                .permitAll())
                .logout(out -> out.logoutSuccessUrl("/"));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
