package com.bytepype.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Getter
@RequiredArgsConstructor
public enum Authority implements GrantedAuthority {

    ;

    private final String authority;

}
