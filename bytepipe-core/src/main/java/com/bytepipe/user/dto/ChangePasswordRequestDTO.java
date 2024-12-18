package com.bytepipe.user.dto;

import com.bytepipe.user.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ChangePasswordRequestDTO {

    @NotBlank
    @Size(min = 8, max = 255)
    private String oldPassword;

    @NotBlank
    @Size(min = 8, max = 255)
    @Pattern(regexp = User.PATTERN_PASSWORD)
    private String newPassword;

}

