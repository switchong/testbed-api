package com.nftgram.web.member.controller;

import com.nftgram.web.member.service.LoginService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;


import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;


@Controller
@RequiredArgsConstructor
public class LoginController {


    private final LoginService loginService;


    @GetMapping("/login")
    public String login(){

        return  "login/nft_login";
    }



    /**
     * 회원 로그인 처리

     */

//    @PostMapping("/login")
//    public String loginProcess(@Valid NftMemberRequest request,
//                               BindingResult result ,
//                               HttpServletResponse  response ) throws GeneralSecurityException, UnsupportedEncodingException, UserPrincipalNotFoundException {
//
////        if (result.hasErrors()){
////            return "login/nft_login";
////
////        }
//
////        //NftMemberLoginResponse loginResponse = (NftMemberLoginResponse) loginService.login(request);
////
////        if (!loginResponse.isLoginFlag()){
////            FieldError fieldError = (FieldError)  loginResponse.getData();
////            result.addError(fieldError);
////            return "login/nft_login";
////
////
////        }
////
////        Cookie memberInfo = (Cookie) loginResponse.getData();
////        response.addCookie(memberInfo);
//
//        return "redirect:/";
//
//    }
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
