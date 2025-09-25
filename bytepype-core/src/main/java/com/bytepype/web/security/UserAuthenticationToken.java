package com.bytepype.web.security;

import com.bytepype.user.User;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;

public class UserAuthenticationToken extends AbstractAuthenticationToken {

    private final Jwt jwt;
    private final User user;

    public UserAuthenticationToken(Jwt jwt, User user, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.jwt = jwt;
        this.user = user;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return jwt;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }
}
