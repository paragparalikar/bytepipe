package com.bytepype.user.web;

import com.bytepype.user.User;
import com.bytepype.user.web.dto.UserDTO;
import com.bytepype.user.web.dto.UserDetailsDTO;
import org.mapstruct.Mapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toUserDTO(User user);

    List<UserDTO> toUserDTOs(List<User> users);

    UserDetailsDTO toUserDetailsDTO(User user);

    default String toAuthority(GrantedAuthority authority) {
        return authority.getAuthority();
    }

}
