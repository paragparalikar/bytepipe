package com.bytepipe.common.security.authentication;

import jakarta.annotation.PostConstruct;
import lombok.Setter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.OAuth2TokenIntrospectionClaimNames;
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionException;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.Instant;
import java.util.*;
import java.util.function.Function;

@Setter
public class CustomOpaqueTokenIntrospector implements OpaqueTokenIntrospector {
    private static final ParameterizedTypeReference<Map<String, Object>> STRING_OBJECT_MAP = new ParameterizedTypeReference<>() {
    };

    private String clientId;
    private String clientSecret;
    private String introspectionUri;
    private RestTemplate restOperations = new RestTemplate();
    private Function<Map<String, Object>, OAuth2AuthenticatedPrincipal> userResolver;

    @PostConstruct
    public void init(){
        restOperations.getInterceptors().add(new BasicAuthenticationInterceptor(clientId, clientSecret));
    }

    @Override
    public OAuth2AuthenticatedPrincipal introspect(String token) {
        if(!StringUtils.hasText(token)) throw new OAuth2IntrospectionException("Opaque token can not be null or empty");
        final RequestEntity<?> requestEntity = convert(token);
        final ResponseEntity<Map<String, Object>> responseEntity = restOperations.exchange(requestEntity, STRING_OBJECT_MAP);
        if (responseEntity.getStatusCode() != HttpStatus.OK) throw new OAuth2IntrospectionException(
                    "Introspection endpoint responded with " + responseEntity.getStatusCode());
        final Map<String, Object> claims = Optional.ofNullable(responseEntity.getBody())
                .map(this::convertClaimsSet)
                .orElse(Map.of());
        return userResolver.apply(claims);
    }

    private RequestEntity<?> convert(String token){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("token", token);
        body.add("access_token", token);
        return new RequestEntity<>(body, headers, HttpMethod.POST, URI.create(introspectionUri));
    }

    private Map<String, Object> convertClaimsSet(Map<String, Object> claims) {
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
