package com.bytepipe.alert.mail.verification;

import com.bytepipe.alert.mail.EmailService;
import com.bytepipe.alert.mail.EmailTemplate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.thymeleaf.context.Context;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class EmailVerificationService {

    private final EmailService emailService;
    private final EmailVerificationRepository repository;
    @Value("${bytepipe.base-url}") private String baseUrl;
    @Value("${bytepipe.verification.mail.expiry-duration:PT24H}") private Duration expiryDuration;

    @SneakyThrows
    public void initiate(@Email @NotBlank String email, @NotBlank String subject,
                         @NotNull EmailTemplate emailTemplate, Context context){
        log.info("Initiating email verification for {}", email);
        final String id = repository.save(new EmailVerification(email)).getId();
        final String verificationLink = baseUrl + "/verifications/" + id;
        context.setVariable("verificationLink", verificationLink);
        emailService.dispatch(email, subject, emailTemplate, context);
    }

    public void verify(@NotBlank String id) throws EmailVerificationNotFoundException, EmailVerificationExpiredException{
        final EmailVerification verification = repository.findById(id).orElseThrow(() -> new EmailVerificationNotFoundException(id));
        if(0 < Duration.between(verification.getCreatedDate(), Instant.now()).compareTo(expiryDuration)){
            throw new EmailVerificationExpiredException(id, verification.getEmail());
        }
        verification.setStatus(EmailVerificationStatus.VERIFIED);
        repository.save(verification);
    }

}
