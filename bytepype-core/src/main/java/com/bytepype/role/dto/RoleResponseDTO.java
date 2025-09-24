package com.bytepype.role.dto;

import lombok.Data;

import java.util.Set;

@Data
public class RoleResponseDTO {

    private Long id;
    private String name;
    private String description;
    private Set<String> authorities;

}
