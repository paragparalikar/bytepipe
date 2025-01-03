package com.bytepipe.common.security;

import com.bytepipe.user.User;
import com.bytepipe.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

@RequiredArgsConstructor
public class UserJwtAuthenticationConverter implements Converter<Jwt, UserAuthenticationToken> {

    private final UserService userService;

    @Override
    public UserAuthenticationToken convert(Jwt jwt) {
        final String email = jwt.getClaimAsString("email");
        if(!StringUtils.hasText(email)) return null;
        final User user = Optional.ofNullable(userService.findByEmail(email))
                .map(managedUser -> updateUser(managedUser, jwt))
                .orElseGet(() -> createUser(jwt));
        return new UserAuthenticationToken(jwt, user, null);
    }

    private User updateUser(User user, Jwt jwt){
        setIfPresent(jwt.getClaimAsString("name"), user::setName);
        setIfPresent(jwt.getClaimAsString("picture"), user::setPicture);
        setIfPresent(jwt.getClaimAsString("given_name"), user::setGivenName);
        setIfPresent(jwt.getClaimAsString("family_name"), user::setFamilyName);
        final Map<String, Object> attributes = Optional.ofNullable(user.getAttributes()).orElseGet(HashMap::new);
        attributes.putAll(jwt.getClaims());
        user.setAttributes(attributes);
        return userService.saveOrUpdate(user);
    }

    private void setIfPresent(String value, Consumer<String> consumer){
        if(StringUtils.hasText(value)) consumer.accept(value);
    }

    private User createUser(Jwt jwt){
        final User user = new User();
        user.setName(jwt.getClaimAsString("name"));
        user.setEmail(jwt.getClaimAsString("email"));
        user.setPicture(jwt.getClaimAsString("picture"));
        user.setGivenName(jwt.getClaimAsString("given_name"));
        user.setFamilyName(jwt.getClaimAsString("family_name"));
        user.setAttributes(jwt.getClaims());
        return userService.saveOrUpdate(user);
    }
}
