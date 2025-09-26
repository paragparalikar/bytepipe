package com.bytepype.role.web;

import com.bytepype.role.Role;
import com.bytepype.role.exception.RoleNotFoundException;
import com.bytepype.role.RoleService;
import com.bytepype.role.web.dto.CreateRoleRequestDTO;
import com.bytepype.role.web.dto.RoleResponseDTO;
import com.bytepype.role.web.dto.UpdateRoleRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public abstract class RoleMapper {

    @Autowired
    private RoleService roleService;

    public abstract RoleResponseDTO toDTO(Role role);

    @Mapping(target="id", ignore = true)
    @Mapping(target="createdBy", ignore = true)
    @Mapping(target="createdDate", ignore = true)
    @Mapping(target="lastModifiedBy", ignore = true)
    @Mapping(target="lastModifiedDate", ignore = true)
    public abstract Role toEntity(CreateRoleRequestDTO dto);

    @Mapping(target="id", ignore = true)
    @Mapping(target="createdBy", ignore = true)
    @Mapping(target="createdDate", ignore = true)
    @Mapping(target="lastModifiedBy", ignore = true)
    @Mapping(target="lastModifiedDate", ignore = true)
    public abstract void update(@MappingTarget Role role, UpdateRoleRequestDTO dto);

    public abstract List<RoleResponseDTO> toDTOs(List<Role> roles);

    public Role toEntity(Long id) throws RoleNotFoundException {
        return roleService.findById(id);
    }

    public abstract Set<Role> toDTOs(Collection<Long> ids) throws RoleNotFoundException ;

}
