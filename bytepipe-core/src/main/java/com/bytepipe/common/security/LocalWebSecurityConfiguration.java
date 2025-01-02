package com.bytepipe.common.security;

import com.bytepipe.common.security.authentication.CompositeOpaqueTokenIntrospector;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.server.resource.authentication.OpaqueTokenAuthenticationProvider;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@Profile("local")
@EnableWebSecurity
@RequiredArgsConstructor
public class LocalWebSecurityConfiguration {

    private final CompositeOpaqueTokenIntrospector opaqueTokenIntrospector;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .cors(config -> config.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(manager -> manager
                        .requestMatchers(HttpMethod.GET, "/h2/**", "/favicon.ico",
                                "**/*.html", "**/*.jpg", "**/*.png", "**/*.js", "**/*.css", "**/*.ttf", "**/*.woff2").permitAll()
                        .anyRequest().authenticated())
                .oauth2ResourceServer(config -> config.authenticationManagerResolver(
                        request -> new ProviderManager(new OpaqueTokenAuthenticationProvider(opaqueTokenIntrospector))))
                .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
