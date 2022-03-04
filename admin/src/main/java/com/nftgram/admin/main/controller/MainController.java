package com.nftgram.admin.main.controller;


import com.nftgram.admin.admin.dto.AdminMemberAuthDto;
import com.nftgram.admin.admin.dto.request.AdminMemberLoginRequest;
import com.nftgram.admin.admin.service.AdminMemberAuthService;
import com.nftgram.admin.common.auth.MemberLoginManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;


@Controller
@RequiredArgsConstructor

public class MainController {


    private final MemberLoginManager memberLoginManager;
    private final AdminMemberAuthService memberAuthService;


    @GetMapping("/")
    public String Main(Model model) throws GeneralSecurityException, UnsupportedEncodingException {
//        model.addAttribute("adminMemberRequest", new AdminMemberLoginRequest());
//        AdminMemberAuthDto authDto = memberLoginManager.getInfo();
//
//        if(authDto.getLoginYN().equals("Y")) {
//            return "index";
//        } else {
//            return  "redirect:/auth/login";
//        }

        return "index";

    }

    @GetMapping("/allNft")
    public String AllNft(Model model) {
        return "pages/allNft";
    }

    @GetMapping("/loadNft")
    public String LoadNft(Model model) {
        return "pages/loadNft";
    }
}
