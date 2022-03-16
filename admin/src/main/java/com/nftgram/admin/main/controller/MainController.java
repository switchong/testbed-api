package com.nftgram.admin.main.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor
public class MainController {


    @GetMapping({"/", "/index"})
    public String Main()  {
        return "index";

    }


//    @GetMapping("/loadNft")
//    public String LoadNft(Model model) {
//        return "pages/loadNft";
//    }
}
