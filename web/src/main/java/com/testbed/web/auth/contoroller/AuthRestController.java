package com.testbed.web.auth.contoroller;

import com.testbed.web.auth.dto.response.AuthorizeResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class AuthRestController {

    @GetMapping("/authResult")
    public AuthorizeResponse AuthResult(AuthorizeResponse authorizeResponse) {
        String strResult = "";

        HttpServletRequest httpServletRequest = null;

        return authorizeResponse;
    }
}
