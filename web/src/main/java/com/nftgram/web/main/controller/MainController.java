package com.nftgram.web.main.controller;

import com.nftgram.web.main.dto.MainResponse;
import com.nftgram.web.main.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    @GetMapping("/")
    public String Main(Model model, Pageable pageable) throws GeneralSecurityException, UnsupportedEncodingException, ParseException {
        String name = "";
        Long nftId = Long.valueOf(1);

//        List<MainResponse> mainResponse = mainService.nftSearch(name);

        List<MainResponse> mainResponseAll = mainService.findAllList(pageable);

//        ArrayList<Nft> mainResponse = mainService.nftIdSearch(nftId);

        model.addAttribute("nftList",mainResponseAll);


//        System.out.print("NFT:::::"+mainResponse.get(0));

        return "index";
    }

}
