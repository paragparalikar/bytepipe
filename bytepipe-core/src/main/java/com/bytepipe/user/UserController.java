package com.bytepipe.user;

import com.bytepipe.alert.mail.EmailTemplate;
import com.bytepipe.alert.mail.verification.EmailVerificationService;
import com.bytepipe.user.dto.RegisterUserRequestDTO;
import com.bytepipe.user.dto.UpdateUserRequestDTO;
import com.bytepipe.user.dto.UserResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.Context;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;
    private final EmailVerificationService emailVerificationService;

    @PostMapping
    public void test(HttpServletRequest request){
        request.getHeaderNames().asIterator().forEachRemaining(headerName -> {
            System.out.println(headerName + " : " +request.getHeader(headerName));
        });
    }


    //@PostMapping
    public UserResponseDTO register(@RequestBody @NotNull @Valid RegisterUserRequestDTO dto) {
        final User user = userMapper.registrationDtoToUser(dto);
        user.setEnabled(false);
        final Context context = new Context();
        context.setVariable("user", user);
        emailVerificationService.initiate(user.getEmail(),
                "Please verify your email",
                EmailTemplate.EMAIL_VERIFICATION,
                context);
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
