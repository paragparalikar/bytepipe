package com.bytepipe.common.security;

import com.bytepipe.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.server.resource.authentication.JwtIssuerAuthenticationManagerResolver;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration {

    private final UserService userService;

    @Value("${spring.security.trusted-issuers}")
    private Set<String> trustedIssuers;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        final UserJwtAuthenticationConverter jwtAuthenticationConverter = new UserJwtAuthenticationConverter(userService);
        final JwtAuthenticationManagerResolver authenticationManagerResolver = new JwtAuthenticationManagerResolver(
                trustedIssuers::contains, jwtAuthenticationConverter);
        return http.csrf(AbstractHttpConfigurer::disable)
                .cors(config -> config.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(manager -> manager
                        .requestMatchers("/h2/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/favicon.ico", "/**/*.html", "/**/*.jpg", "/**/*.png", "/**/*.js", "/**/*.css", "**/*.ttf", "/**/*.woff2").permitAll()
                        .anyRequest().authenticated())
                .oauth2ResourceServer(config -> config.authenticationManagerResolver(
                        new JwtIssuerAuthenticationManagerResolver(authenticationManagerResolver)))
                .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}

