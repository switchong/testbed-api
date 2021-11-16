package com.nftgram.web.controller;


import com.nftgram.core.domain.nftgram.NftMember;
import com.nftgram.web.controller.service.LoginService;
import com.nftgram.web.dto.request.NftMemberRequest;
import com.nftgram.web.dto.response.NftMemberLoginResponse;
import lombok.RequiredArgsConstructor;

import org.apache.logging.log4j.message.Message;
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
public class LoginController {


    private final LoginService loginService;


    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("NftMemberRequest" , new NftMemberRequest());
        return  "login/nft_login";
    }



    /**
     * 회원 로그인 처리

     */

    @PostMapping("/login")
    public String loginProcess(@Valid NftMemberRequest request,
                               BindingResult result ,
                               HttpServletResponse  response ) throws GeneralSecurityException, UnsupportedEncodingException, UserPrincipalNotFoundException {

//        if (result.hasErrors()){
//            return "login/nft_login";
//
//        }

//        //NftMemberLoginResponse loginResponse = (NftMemberLoginResponse) loginService.login(request);
//
//        if (!loginResponse.isLoginFlag()){
//            FieldError fieldError = (FieldError)  loginResponse.getData();
//            result.addError(fieldError);
//            return "login/nft_login";
//
//
//        }
//
//        Cookie memberInfo = (Cookie) loginResponse.getData();
//        response.addCookie(memberInfo);

        return "redirect:/";

    }
    /*
    회원가입 페이지 이동
     */
    @GetMapping("/signup")
    public String goSignup() {
        return "login/signup";
    }
    @PostMapping("/signup")
    public String signup(HttpServletRequest request) {
        //memberService.joinUser(nftMember);
        return "redirect:/login";
    }




    @PostMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session =request.getSession(false);
        if (session != null){
            session.invalidate();
        }
        return "redirect:/";
    }
}
