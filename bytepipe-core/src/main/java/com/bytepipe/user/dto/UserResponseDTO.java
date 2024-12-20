package com.bytepipe.user.dto;

import lombok.Data;

import java.util.Map;

@Data
public class UserResponseDTO {

    private Long id;
    private String email;
    private String givenName;
    private String familyName;
    private Map<String, String> properties;

}
