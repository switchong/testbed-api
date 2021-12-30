package com.nftgram.web.member.controller;

import com.nftgram.web.common.auth.MemberLoginManager;
import com.nftgram.web.member.dto.NftMemberAuthDto;
import com.nftgram.web.member.dto.request.NftMemberLoginRequest;
import com.nftgram.web.member.service.MemberAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")

public class MemberController {

    private final MemberAuthService memberAuthService;

    private final MemberLoginManager memberLoginManager;

    @GetMapping("/mypage")
    public String mypage(Model model) throws GeneralSecurityException, UnsupportedEncodingException {
        model.addAttribute("nftMemberLoginRequest", new NftMemberLoginRequest());
        NftMemberAuthDto authDto = memberLoginManager.getInfo();
        if(authDto.getLoginYN().equals("Y")) {
            return  "member/mypage";
        } else {
            return "redirect:/auth/login";
        }
    }

    @GetMapping("/mywallet")
    public String wallet(Model model) throws GeneralSecurityException, UnsupportedEncodingException {
        model.addAttribute("nftMemberLoginRequest", new NftMemberLoginRequest());
        NftMemberAuthDto authDto = memberLoginManager.getInfo();
        if(authDto.getLoginYN().equals("Y")) {
            return  "member/mywallet";
        } else {
            return "redirect:/auth/login";
        }
    }
}
