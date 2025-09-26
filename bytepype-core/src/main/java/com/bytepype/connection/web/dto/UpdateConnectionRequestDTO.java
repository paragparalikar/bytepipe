package com.bytepype.connection.web.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = UpdateOracleConnectionRequestDTO.class, name = "ORACLE")
})
public abstract class UpdateConnectionRequestDTO {

    @NotBlank
    @Size(min = 3, max = 255)
    private String name;

    @Size(max = 255)
    private String description;

}
