package com.bytepipe.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ResetPasswordRequestDTO {

    @NotNull @Positive private Long id;
    @NotBlank @Size(max = 255) private String oldPassword;
    @NotBlank @Size(max = 255) private String newPassword;

}

