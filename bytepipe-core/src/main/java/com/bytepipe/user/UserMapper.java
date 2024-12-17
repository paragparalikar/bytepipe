package com.bytepipe.user;

import com.bytepipe.role.RoleMapper;
import com.bytepipe.user.dto.RegisterUserRequestDTO;
import com.bytepipe.user.dto.UpdateUserRequestDTO;
import com.bytepipe.user.dto.UserResponseDTO;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UserMapper {

    @Mapping(target="createdBy", ignore = true)
    @Mapping(target="createdDate", ignore = true)
    @Mapping(target="lastModifiedBy", ignore = true)
    @Mapping(target="lastModifiedDate", ignore = true)
    @Mapping(target="enabled", ignore = true)
    @Mapping(target="id", ignore = true)
    @Mapping(target="identityProvider", ignore = true)
    @Mapping(target="authorities", ignore = true)
    User registrationDtoToUser(RegisterUserRequestDTO dto);

    @InheritConfiguration
    void update(@MappingTarget User user, UpdateUserRequestDTO dto);

    UserResponseDTO toDTO(User user);

}
