package com.bytepipe.user;

import com.bytepipe.alert.mail.verification.EmailVerificationService;
import jakarta.validation.Valid;
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
    private final EmailVerificationService emailVerificationService;

    public User findById(@NonNull Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }


    public User create(@NotNull @Valid User user) throws UserExistsException {
        if(userRepository.existsByEmail(user.getEmail())){
            throw new UserExistsException(user.getEmail());
        }
        user.setEnabled(!IdentityProvider.SELF.equals(user.getIdentityProvider()));
        return userRepository.save(user);
    }

    public User update(@NotNull @Valid User user){
        if(null == user.getId()) throw new IllegalArgumentException("User id can not be null");
        return userRepository.save(user);
    }


}
