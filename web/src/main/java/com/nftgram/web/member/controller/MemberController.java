package com.nftgram.web.member.controller;

import com.nftgram.web.common.auth.MemberLoginManager;
import com.nftgram.web.member.dto.request.NftMemberLoginRequest;
import com.nftgram.web.member.service.MemberAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")

public class MemberController {

    private final MemberAuthService memberAuthService;

    private final MemberLoginManager memberLoginManager;

    @GetMapping("/mypage")
    public String mypage(Model model) {
        model.addAttribute("nftMemberLoginRequest", new NftMemberLoginRequest());
        return  "member/mypage";
    }

    @GetMapping("/mywallet")
    public String wallet(Model model) {
        model.addAttribute("nftMemberLoginRequest", new NftMemberLoginRequest());
        return  "member/mywallet";
    }
}
