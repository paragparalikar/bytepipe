package com.bytepype.user.web.dto;

import lombok.Builder;

import java.util.Map;
import java.util.Set;

@Builder
public record UserDetailsDTO(String id, String name, String givenName,
                             String familyName, String picture, String email,
                             Map<String, Object> attributes, Set<String> authorities) {

}
