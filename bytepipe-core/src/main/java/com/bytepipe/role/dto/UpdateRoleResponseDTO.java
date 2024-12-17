package com.bytepipe.role.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UpdateRoleResponseDTO {

    private Long id;
    private String name;
    private String description;
    private Set<String> authorities;

}
