package com.nftgram.web.member.controller;

import com.nftgram.web.common.auth.MemberLoginManager;
import com.nftgram.web.member.dto.request.NftMemberLoginRequest;
import com.nftgram.web.member.dto.request.NftMemberSignupRequest;
import com.nftgram.web.member.dto.response.NftMemberLoginResponse;
import com.nftgram.web.member.dto.response.NftMemberSignupResponse;
import com.nftgram.web.member.service.MemberAuthService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;


import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.security.GeneralSecurityException;


@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")

public class AuthController {

    private final MemberAuthService memberAuthService;

    private final MemberLoginManager memberLoginManager;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("nftMemberLoginRequest", new NftMemberLoginRequest());
        return  "auth/nft_login";
    }

    /**
     * 회원 로그인 처리
     */
    @PostMapping("/login")
    public String loginProcess(@Valid NftMemberLoginRequest request,
                               BindingResult result ,
                               HttpServletResponse response ) throws GeneralSecurityException, UnsupportedEncodingException, UserPrincipalNotFoundException {

        NftMemberLoginResponse nftMemberLoginResponse = memberAuthService.loginProcess(request);

        if(!nftMemberLoginResponse.isFlag()) {
            FieldError fieldError = (FieldError) nftMemberLoginResponse.getData();
            result.addError(fieldError);
            return "auth/nft_login";
        }

        Cookie memberInfo = (Cookie) nftMemberLoginResponse.getData();
        response.addCookie(memberInfo);

        return "redirect:/";
    }

    /**
     * 회원가입 페이지 이동
     * @return
     */
    @GetMapping("/signup")
    public String goSignup(Model model) {
        model.addAttribute("nftMemberSignupRequest", new NftMemberSignupRequest());
        return "auth/signup";
    }

    /**
     * 회원가입 처리
     * @param request
     * @return
     */
    @PostMapping("/signup")
    public String signup(@Valid NftMemberSignupRequest request,
                         BindingResult result) {
        NftMemberSignupResponse nftMemberSignupResponse = memberAuthService.memberJoinProcess(request);
        if(!nftMemberSignupResponse.isFlag()) {
            FieldError fieldError = (FieldError) nftMemberSignupResponse.getData();
            result.addError(fieldError);

            return "auth/signup";
        }

        return "redirect:/auth/login";
    }

    /**
     * 로그아웃
     * @param response
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpServletResponse response){
        Cookie expireCookie = memberLoginManager.expire();
        response.addCookie(expireCookie);

        return "redirect:/";
    }
}
