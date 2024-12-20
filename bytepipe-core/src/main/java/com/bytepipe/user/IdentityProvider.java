package com.bytepipe.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum IdentityProvider {

    SELF("self"),
    GOOGLE("google"),
    LINKEDIN("linkedin"),
    GITHUB("github");

    private final String clientId;

    public static IdentityProvider findByClientId(String clientId){
        if(!StringUtils.hasText(clientId)) return null;
        return Arrays.stream(IdentityProvider.values())
                .filter(provider -> clientId.trim().equalsIgnoreCase(provider.getClientId()))
                .findFirst()
                .orElse(null);
    }

}
