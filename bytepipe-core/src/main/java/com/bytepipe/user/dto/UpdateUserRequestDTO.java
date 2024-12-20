package com.bytepipe.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class UpdateUserRequestDTO {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(max = 255)
    private String givenName;

    @NotBlank
    @Size(max = 255)
    private String familyName;

    @Size(max = 255)
    private Map<@NotBlank @Size(max = 255) String, @NotBlank @Size(max = 255) String> properties = new HashMap<>();


}
