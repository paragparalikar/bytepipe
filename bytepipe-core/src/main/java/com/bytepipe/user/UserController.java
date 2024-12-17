package com.bytepipe.user;

import com.bytepipe.user.dto.RegisterUserRequestDTO;
import com.bytepipe.user.dto.UpdateUserRequestDTO;
import com.bytepipe.user.dto.UserResponseDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;

    @PostMapping
    public UserResponseDTO register(@RequestBody @NotNull @Valid RegisterUserRequestDTO dto) throws UserExistsException {
        final User user = userMapper.registrationDtoToUser(dto);
        user.setIdentityProvider(IdentityProvider.SELF);
        final User managedUser = userService.create(user);
        return userMapper.toDTO(managedUser);
    }

    @PutMapping("/{id}")
    public UserResponseDTO update(@PathVariable @NotNull Long id, @RequestBody @NotNull @Valid UpdateUserRequestDTO dto) throws UserNotFoundException {
        final User user = userService.findById(id);
        userMapper.update(user, dto);
        final User managedUser = userService.update(user);
        return userMapper.toDTO(managedUser);
    }


}
