package com.bytepipe.user.dto;

import com.bytepipe.user.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterUserRequestDTO {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 8, max = 255)
    @Pattern(regexp = User.PATTERN_PASSWORD)
    private String password;

    @NotBlank
    @Size(max = 255)
    private String givenName;

    @NotBlank
    @Size(max = 255)
    private String familyName;

}
