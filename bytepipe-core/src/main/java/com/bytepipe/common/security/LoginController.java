package com.bytepipe.common.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping({"/signin", "/signout"})
    public String signin(){
        return "signin";
    }

    // TODO add captcha
    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }

}
