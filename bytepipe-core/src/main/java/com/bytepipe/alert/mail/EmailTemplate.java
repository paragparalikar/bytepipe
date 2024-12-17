package com.bytepipe.alert.mail;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EmailTemplate {

    EMAIL_VERIFICATION("email-verification");

    private final String fileName;


}
