package com.nftgram.web.main.controller;

import com.nftgram.core.domain.nftgram.Nft;
import com.nftgram.web.main.dto.MainResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.text.ParseException;

@Controller
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/")
    public String Main(Model model) throws GeneralSecurityException, UnsupportedEncodingException, ParseException {
        MainResponse mainResponse = new MainResponse();

        Nft nft = mainResponse.getNft();


        return "index";
    }
}
