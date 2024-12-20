package com.bytepipe.user;

import com.bytepipe.role.RoleMapper;
import com.bytepipe.user.dto.RegisterUserRequestDTO;
import com.bytepipe.user.dto.UpdateUserRequestDTO;
import com.bytepipe.user.dto.UserResponseDTO;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public abstract class UserMapper {

    @Mapping(target="createdBy", ignore = true)
    @Mapping(target="createdDate", ignore = true)
    @Mapping(target="lastModifiedBy", ignore = true)
    @Mapping(target="lastModifiedDate", ignore = true)
    @Mapping(target="enabled", ignore = true)
    @Mapping(target="id", ignore = true)
    @Mapping(target="authorities", ignore = true)
    public abstract User registrationDtoToUser(RegisterUserRequestDTO dto);

    @InheritConfiguration
    public abstract void update(@MappingTarget User user, UpdateUserRequestDTO dto);

    public abstract UserResponseDTO toDTO(User user);

    @InheritConfiguration(name = "fromOAuth2User")
    public abstract User fromOidcUser(OidcUser oidcUser);

    @Mapping(target = "authorities", ignore = true)
    @Mapping(target = "enabled", constant = "true")
    @Mapping(target = "email", source = "attributes.email")
    @Mapping(target = "name", source = "attributes.name")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    public abstract User fromOAuth2User(OAuth2User oAuth2User);

    public String map(Object value){
        return null == value ? "" : value.toString();
    }

}
