package com.bytepipe.common.security;

import lombok.Data;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.oauth2.server.resource.authentication.OpaqueTokenAuthenticationProvider;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.security.oauth2.server.resource.introspection.SpringOpaqueTokenIntrospector;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URI;
import java.util.Collections;

@Data
public class OpaqueTokenConfig {

    private String introspectionUri;
    private String clientId;
    private String clientSecret;

    public AuthenticationManager createAuthenticationManager(){
        final SpringOpaqueTokenIntrospector opaqueTokenIntrospector = new SpringOpaqueTokenIntrospector(introspectionUri,clientId,clientSecret);
        final AuthenticationProvider authenticationProvider = new OpaqueTokenAuthenticationProvider(opaqueTokenIntrospector);
        return new ProviderManager(authenticationProvider);
    }

}
