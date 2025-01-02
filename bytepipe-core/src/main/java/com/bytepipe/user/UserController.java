package com.bytepipe.user;

import com.nimbusds.jose.util.IOUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController{

    @PostMapping
    public void print(HttpServletRequest request) throws IOException {
        request.getHeaderNames().asIterator().forEachRemaining(headerName -> {
            System.out.println(headerName + " : " + request.getHeader(headerName));
        });
        System.out.println();
        final String body = IOUtils.readInputStreamToString(request.getInputStream());
        System.out.println(body);
    }

}

