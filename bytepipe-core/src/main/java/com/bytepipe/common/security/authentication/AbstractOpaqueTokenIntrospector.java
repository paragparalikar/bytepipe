package com.bytepipe.common.security.authentication;

import com.bytepipe.user.User;
import com.bytepipe.user.UserService;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.OAuth2TokenIntrospectionClaimNames;
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionException;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.Instant;
import java.util.*;

@Slf4j
@Setter
public class AbstractOpaqueTokenIntrospector implements OpaqueTokenIntrospector {
    private static final ParameterizedTypeReference<Map<String, Object>> STRING_OBJECT_MAP = new ParameterizedTypeReference<>() {
    };

    private String clientId;
    private String clientSecret;
    private String userInfoUri;
    private UserService userService;
    private final RestTemplate restOperations = new RestTemplate();

    @PostConstruct
    public void init(){
        restOperations.getInterceptors().add(new BasicAuthenticationInterceptor(clientId, clientSecret));
    }

    @Override
    public OAuth2AuthenticatedPrincipal introspect(String token) {
        try{
            final RequestEntity<?> requestEntity = convert(token);
            final ResponseEntity<Map<String, Object>> responseEntity = restOperations.exchange(requestEntity, STRING_OBJECT_MAP);
            if (responseEntity.getStatusCode() != HttpStatus.OK) throw new OAuth2IntrospectionException(
                    "Introspection endpoint responded with " + responseEntity.getStatusCode());
            final Map<String, Object> claims = Optional.ofNullable(responseEntity.getBody())
                    .map(this::convertClaimsSet)
                    .orElse(Map.of());
            return convertAndPersist(claims);
        } catch(Exception e){
            log.error("Error while introspecting opaque token", e);
            throw new OAuth2IntrospectionException(e.getMessage(), e);
        }
    }

    protected RequestEntity<?> convert(String token){
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(token);
        return new RequestEntity<>(headers, HttpMethod.GET, URI.create(userInfoUri));
    }

    protected OAuth2AuthenticatedPrincipal convertAndPersist(Map<String, Object> claims){
        claims.forEach((key, value) -> System.out.println(key + " : " + value));
        final String email = String.valueOf(claims.get("email"));
        final User user = Optional.ofNullable(userService.findByEmail(email)).orElseGet(User::new);
        user.setEmail(email);
        user.setName(String.valueOf(claims.get("name")));
        final Map<String, Object> attributes = Optional.ofNullable(user.getAttributes()).orElseGet(HashMap::new);
        attributes.putAll(claims);
        user.setAttributes(attributes);
        userService.saveOrUpdate(user);
        return user;
    }


    protected Map<String, Object> convertClaimsSet(Map<String, Object> claims) {
        claims = new LinkedHashMap<>(claims);
        claims.computeIfPresent(OAuth2TokenIntrospectionClaimNames.AUD, (k, v) -> {
            if (v instanceof String) {
                return Collections.singletonList(v);
            }
            return v;
        });
        claims.computeIfPresent(OAuth2TokenIntrospectionClaimNames.CLIENT_ID, (k, v) -> v.toString());
        claims.computeIfPresent(OAuth2TokenIntrospectionClaimNames.EXP,
                (k, v) -> Instant.ofEpochSecond(((Number) v).longValue()));
        claims.computeIfPresent(OAuth2TokenIntrospectionClaimNames.IAT,
                (k, v) -> Instant.ofEpochSecond(((Number) v).longValue()));
        claims.computeIfPresent(OAuth2TokenIntrospectionClaimNames.ISS, (k, v) -> v.toString());
        claims.computeIfPresent(OAuth2TokenIntrospectionClaimNames.NBF,
                (k, v) -> Instant.ofEpochSecond(((Number) v).longValue()));
        claims.computeIfPresent(OAuth2TokenIntrospectionClaimNames.SCOPE,
                (k, v) -> (v instanceof String s) ? Arrays.asList(s.split(" ")) : v);
        return claims;
    }
}
