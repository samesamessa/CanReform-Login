package org.iclass.board.config;

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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@Slf4j
@EnableMethodSecurity(prePostEnabled = true)

public class SecurityConfig {
    private static final String[] PERMIT_LIST = {
            "/", "/signup", "/login", "/js/**", "/api/**", "/index", "/favicon.ico", "/error", "/css/**",
            "/bulletin-board"
    };

    @Bean
    public SecurityFilterChain filterchain(HttpSecurity http, CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler) throws Exception {
        log.info(":::::: Custom Security Filter Chain 작동중");
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(r -> r
                        .requestMatchers(PERMIT_LIST)
                        .permitAll()
                        .anyRequest()
                        .authenticated()
        )
        .formLogin(form -> form.loginPage("/login")
                .successHandler(customAuthenticationSuccessHandler)
                .defaultSuccessUrl("http://localhost:5173/")
                .usernameParameter("username")
                .permitAll())
                .logout(out -> out.logoutSuccessUrl("/"));

        return http.build();
    }

/*    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:5173", "http://localhost:8080")); // React 앱의 출처
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowCredentials(true); // 인증 정보 포함
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }*/


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
