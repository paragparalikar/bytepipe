package com.bytepype.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController{

    @PostMapping
    public void print(@AuthenticationPrincipal(errorOnInvalidType = true) User user) throws IOException {
        System.out.println(user);
    }

}

