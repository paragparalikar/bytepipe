package com.bytepype.project.dto;

import lombok.Data;

import java.util.Map;

@Data
public class UpdateProjectRequestDTO {

    private Long id;
    private String name;
    private String description;
    private Map<String, String> attributes;

}
