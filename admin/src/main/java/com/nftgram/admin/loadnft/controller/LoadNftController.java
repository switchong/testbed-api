package com.nftgram.admin.loadnft.controller;



import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class LoadNftController {


    @GetMapping("/loadNft")
    public String loadNFt() {

        return "pages/loadNft";
    }
}
