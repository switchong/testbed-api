package com.nftgram.web.member.controller;

import com.nftgram.core.domain.dto.NftMemberDto;
import com.nftgram.core.domain.service.NftMemberService;
import com.nftgram.web.member.service.LoginService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;



import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;



@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final NftMemberService nftMemberService;

    @GetMapping("/login")
    public String login(){

        return  "login/nft_login";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request , HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null){
            new SecurityContextLogoutHandler().logout(request , response ,authentication);
        }
        return "redirect:/";
    }




    /*
    회원가입 페이지 이동
     */
    @GetMapping("/signup")
    public String signup() {
        return "login/signup";

    }


    @PostMapping("/signup")
    public  String signup(NftMemberDto nftMember){
        nftMemberService.signup(nftMember);
        return "login/nft_login";
    }

}

