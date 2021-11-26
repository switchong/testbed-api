package com.nftgram.web.main.controller;

import com.nftgram.core.domain.dto.NftMemberDto;
import com.nftgram.core.domain.nftgram.Nft;
import com.nftgram.core.domain.nftgram.NftMember;
import com.nftgram.web.main.dto.MainResponse;
import com.nftgram.web.member.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.text.ParseException;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {


    private final LoginService loginService;

    @GetMapping("/")
    public String Main(Model model) throws GeneralSecurityException, UnsupportedEncodingException, ParseException {
        MainResponse mainResponse = new MainResponse();

        Nft nft = mainResponse.getNft();


        return "index";
    }

}
