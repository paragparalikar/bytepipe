package com.bytepype.role.web.dto;

import lombok.Data;

import java.util.Set;

@Data
public class CreateRoleResponseDTO {

    private Long id;
    private String name;
    private String description;
    private Set<String> authorities;

}
