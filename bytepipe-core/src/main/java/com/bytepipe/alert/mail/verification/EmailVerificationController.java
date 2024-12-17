package com.bytepipe.alert.mail.verification;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/verifications")
public class EmailVerificationController {

    private final EmailVerificationService emailVerificationService;

    @GetMapping("/{id}")
    public String verify(@PathVariable("id") @NotBlank String id){
        try {
            emailVerificationService.verify(id);
            return "email-verification-success";
        } catch (EmailVerificationNotFoundException e) {
            return "email-verification-not-found";
        } catch (EmailVerificationExpiredException e) {
            return "email-verification-expired";
        }
    }


}
