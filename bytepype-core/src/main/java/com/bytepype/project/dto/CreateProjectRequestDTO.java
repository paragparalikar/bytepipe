package com.bytepype.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class CreateProjectRequestDTO {

    @NotBlank
    @Size(min = 3, max = 255)
    private String name;

    @Size(max = 255)
    private String description;

    @Size(max = 255)
    private Map<@NotBlank @Size(max = 255) String, @NotBlank @Size(max = 255) String> attributes = new HashMap<>();

}
