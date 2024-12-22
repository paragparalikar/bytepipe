package com.bytepipe.common.security;

import com.bytepipe.user.User;
import com.bytepipe.user.UserMapper;
import com.bytepipe.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import java.util.Optional;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration {

    private final UserMapper userMapper;
    private final UserService userService;
    private final OidcUserService oidcUserService = new OidcUserService();
    private final DefaultOAuth2UserService oauth2UserService = new DefaultOAuth2UserService();

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(manager -> manager
                        .requestMatchers(HttpMethod.POST, "/users").permitAll()
                        .requestMatchers(HttpMethod.GET, "/signup", "/error", "/h2/**", "/images/**", "/fonts/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(config -> config.loginPage("/signin").permitAll())
                .oauth2Login(config -> config.userInfoEndpoint(infoConfig -> infoConfig
                        .userService(this::loadUser)
                        .oidcUserService(this::loadUser)))
                .logout(config -> config.logoutSuccessUrl("/signout").permitAll())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PasswordGenerator passwordGenerator(){
        return new PasswordGenerator();
    }

    @Bean
    public UserDetailsPasswordService userDetailsPasswordService(){
        return (user, newPassword) -> {
            final PasswordEncoder passwordEncoder = passwordEncoder();
            final User managedUser = userService.findByEmail(user.getUsername());
            managedUser.setPassword(passwordEncoder.encode(newPassword));
            userService.update(managedUser);
            return managedUser;
        };
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> Optional.ofNullable(userService.findByEmail(username))
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Bean
    public AuthenticationProvider usernamePasswordAuthenticationProvider(){
        final PasswordEncoder passwordEncoder = passwordEncoder();
        final DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsPasswordService(userDetailsPasswordService());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        return daoAuthenticationProvider;
    }

    private OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        final OidcUser oidcUser = oidcUserService.loadUser(userRequest);
        return loadUser(oidcUser);
    }

    private OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        final OAuth2User oauth2User = oauth2UserService.loadUser(userRequest);
        return loadUser(oauth2User);
    }

    private User loadUser(OAuth2User oauth2User) throws OAuth2AuthenticationException {
        final String email = oauth2User.getAttribute("email");
        if(null == email) throw new OAuth2AuthenticationException("email not found for " + oauth2User.getName());
        return Optional.ofNullable(userService.findByEmail(email)).orElseGet(() -> {
            final User newUser = OidcUser.class.isAssignableFrom(oauth2User.getClass()) ?
                    userMapper.fromOidcUser((OidcUser) oauth2User) :
                    userMapper.fromOAuth2User(oauth2User);
            log.info("New user logged in using oauth2 : {}", newUser);
            final String password = passwordGenerator().generate(User.DEFAULT_PASSWORD_LENGTH);
            final String encodedPassword = passwordEncoder().encode(password);
            newUser.setPassword(encodedPassword);
            return userService.create(newUser);
        });
    }

}