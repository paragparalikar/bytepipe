package com.bytepipe.common.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class Oauth2AuthenticationManagerResolver implements AuthenticationManagerResolver<HttpServletRequest> {

    private final Map<String, AuthenticationManager> authenticationManagers = new HashMap<>();

    public Oauth2AuthenticationManagerResolver(Oauth2ClientRegistrations registrations){
        authenticationManagers.put("", new NoTenantAuthenticationManager());
        authenticationManagers.put("google", registrations.getGoogle().createAuthenticationManager());
        authenticationManagers.put("microsoft", registrations.getMicrosoft().createAuthenticationManager());
        authenticationManagers.put("linkedin", registrations.getLinkedin().createAuthenticationManager());
        authenticationManagers.put("github", registrations.getGithub().createAuthenticationManager());
    }

    @Override
    public AuthenticationManager resolve(HttpServletRequest request) {
        final String oauth2ConfigId = Optional.ofNullable(request.getHeader("oauth2-config-id"))
                .map(String::trim)
                .map(String::toLowerCase)
                .orElse("");
        return authenticationManagers.get(oauth2ConfigId);
    }
}

class NoTenantAuthenticationManager implements AuthenticationManager {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        throw new BadCredentialsException("No tenant information provided");
    }
}