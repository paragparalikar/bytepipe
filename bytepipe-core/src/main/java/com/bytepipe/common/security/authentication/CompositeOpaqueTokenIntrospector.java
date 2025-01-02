package com.bytepipe.common.security.authentication;

import com.bytepipe.user.UserService;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionException;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Component
@ConfigurationProperties("spring.security.oauth2.client.registration")
public class CompositeOpaqueTokenIntrospector implements OpaqueTokenIntrospector {

    private final UserService userService;
    private final List<OpaqueTokenIntrospector> delegates = new ArrayList<>();
    private final AbstractOpaqueTokenIntrospector google = new AbstractOpaqueTokenIntrospector();
    private final AbstractOpaqueTokenIntrospector microsoft = new AbstractOpaqueTokenIntrospector();
    private final AbstractOpaqueTokenIntrospector linkedin = new AbstractOpaqueTokenIntrospector();
    private final OpaqueTokenIntrospector noopIntrospector = token -> {
        throw new OAuth2IntrospectionException("No tenant information available in token");
    };

    @PostConstruct
    public void init(){
        google.setUserService(userService);
        microsoft.setUserService(userService);
        linkedin.setUserService(userService);
        delegates.addAll(Arrays.asList(noopIntrospector, google, microsoft, linkedin));
    }

    @Override
    @Cacheable("cache-token-user")
    public OAuth2AuthenticatedPrincipal introspect(String token) {
        if(!StringUtils.hasText(token)) throw new OAuth2IntrospectionException("Token can not be null or empty");
        final OpaqueTokenIntrospector delegate = resolveDelegate(token);
        token = token.substring(0, token.length() - 1);
        return delegate.introspect(token);
    }

    private OpaqueTokenIntrospector resolveDelegate(String token){
        try {
            final int index = Integer.parseInt(String.valueOf(token.charAt(token.length() - 1)));
            return delegates.get(index + 1);
        } catch (NumberFormatException e) {
            throw new OAuth2IntrospectionException("Invalid token - no introspection provider found", e);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new OAuth2IntrospectionException("Invalid token - wrong index of introspection provider", e);
        } catch (Exception e) {
            throw new OAuth2IntrospectionException(e.getMessage(), e);
        }
    }

}
