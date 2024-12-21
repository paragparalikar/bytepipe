package com.bytepipe.user;

import com.bytepipe.alert.mail.EmailTemplate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.thymeleaf.context.Context;

@Service
@Validated
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findById(@NonNull Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public User findByEmail(@NonNull @Email final String email){
        return userRepository.findByEmail(email);
    }

    public User create(@NotNull @Valid User user) {
        if(userRepository.existsByEmail(user.getEmail())) return user;
        return userRepository.save(user);
    }

    public User update(@NotNull @Valid User user){
        if(null == user.getId()) throw new IllegalArgumentException("User id can not be null");
        return userRepository.save(user);
    }


}
