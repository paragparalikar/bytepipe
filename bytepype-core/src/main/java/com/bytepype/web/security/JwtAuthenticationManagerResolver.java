package com.bytepype.web.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationManagerResolver implements AuthenticationManagerResolver<String> {

    private final Predicate<String> trustedIssuer;
    private final Converter<Jwt, UserAuthenticationToken> authenticationTokenConverter;
    private final Map<String, AuthenticationManager> authenticationManagers = new ConcurrentHashMap<>();

    @Override
    public AuthenticationManager resolve(String issuer) {
        if (this.trustedIssuer.test(issuer)) {
            AuthenticationManager authenticationManager = this.authenticationManagers.computeIfAbsent(issuer,
                    (k) -> {
                        log.debug("Constructing AuthenticationManager");
                        JwtDecoder jwtDecoder = JwtDecoders.fromIssuerLocation(issuer);
                        final JwtAuthenticationProvider provider = new JwtAuthenticationProvider(jwtDecoder);
                        provider.setJwtAuthenticationConverter(authenticationTokenConverter);
                        return provider::authenticate;
                    });
            log.debug("Resolved AuthenticationManager for issuer {}", issuer);
            return authenticationManager;
        } else {
            log.warn("Did not resolve AuthenticationManager since issuer is not trusted : {}", issuer);
        }
        return null;
    }

}
