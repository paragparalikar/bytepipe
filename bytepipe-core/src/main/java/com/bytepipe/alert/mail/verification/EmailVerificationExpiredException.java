package com.bytepipe.alert.mail.verification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EmailVerificationExpiredException extends Exception {

    private String id;
    private String email;

}
