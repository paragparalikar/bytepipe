package com.bytepype.connector.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UpdateOracleConnectorRequestDTO extends UpdateConnectorRequestDTO {

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
