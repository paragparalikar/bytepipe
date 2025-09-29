package com.bytepype.user.web;

import com.bytepype.user.User;
import com.bytepype.user.UserService;
import com.bytepype.user.web.dto.UserDTO;
import com.bytepype.user.web.dto.UserDetailsDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;

    @GetMapping("/self")
    public UserDetailsDTO getSelf(@AuthenticationPrincipal User user){
        return userMapper.toUserDetailsDTO(user);
    }

    @GetMapping
    public List<UserDTO> findAll(){
        return userMapper.toUserDTOs(userService.findAll());
    }

    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable("id") @NotBlank @Size(min = 3, max = 255) final String id){
        return userMapper.toUserDTO(userService.findById(id));
    }

}
