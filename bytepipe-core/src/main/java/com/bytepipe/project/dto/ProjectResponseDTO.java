package com.bytepipe.project.dto;

import lombok.Data;

import java.util.Map;

@Data
public class ProjectResponseDTO {

    private Long id;
    private String name;
    private String description;
    private Map<String, String> attributes;

}
