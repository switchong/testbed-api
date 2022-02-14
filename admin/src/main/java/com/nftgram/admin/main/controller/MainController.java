package com.nftgram.admin.main.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {

    @GetMapping("/")
    public String Main(Model model) {

        return "index";
    }

    @GetMapping("/login")
    public String Login(Model model) {
        return "auth/login";
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
