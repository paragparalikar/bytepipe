package com.bytepipe.user;

import com.bytepipe.alert.mail.EmailTemplate;
import com.bytepipe.alert.mail.verification.EmailVerificationService;
import jakarta.validation.Valid;
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
    private final EmailVerificationService emailVerificationService;

    public User findById(@NonNull Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }


    public User create(@NotNull @Valid User user) throws UserExistsException {
        if(userRepository.existsByEmail(user.getEmail())){
            throw new UserExistsException(user.getEmail());
        }
        if(IdentityProvider.SELF.equals(user.getIdentityProvider())){
            user.setEnabled(false);
            final Context context = new Context();
            context.setVariable("user", user);
            emailVerificationService.initiate(user.getEmail(),
                    "Please verify your email",
                    EmailTemplate.EMAIL_VERIFICATION,
                    context);
        }

        return userRepository.save(user);
    }

    public User update(@NotNull @Valid User user){
        if(null == user.getId()) throw new IllegalArgumentException("User id can not be null");
        return userRepository.save(user);
    }


}
