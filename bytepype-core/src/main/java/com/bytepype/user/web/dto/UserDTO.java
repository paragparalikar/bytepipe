package com.bytepype.user.web.dto;

import lombok.Builder;

@Builder
public record UserDTO(String id, String name, String givenName, String familyName, String picture) {

}
