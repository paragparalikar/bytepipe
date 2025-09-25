package com.bytepype.web.connection.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CreateOracleConnectionRequestDTO extends CreateConnectionRequestDTO {

    @NotBlank
    @Size(min = 3, max = 255)
    private String url;

    @NotBlank
    @Size(min = 3, max = 255)
    private String username;

    @NotBlank
    @Size(min = 3, max = 255)
    private String password;

}
