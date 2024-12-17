package com.bytepipe.alert.mail.verification;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class EmailVerificationNotFoundException extends Exception {

    private final String id;

}
