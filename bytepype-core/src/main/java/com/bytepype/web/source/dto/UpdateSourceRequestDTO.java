package com.bytepype.web.source.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UpdateSourceRequestDTO {

    @NotBlank
    @Size(min = 3, max = 255)
    String name;

    boolean enabled;

    @Size(max = 255)
    String description;

}
