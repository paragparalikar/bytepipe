package com.bytepipe.user;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findByEmail(@NonNull @Email final String email){
        return userRepository.findByEmail(email);
    }

    public User saveOrUpdate(@Valid User user){
        return userRepository.save(user);
    }

}
