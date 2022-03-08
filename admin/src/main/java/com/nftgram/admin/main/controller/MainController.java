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

//
//    @GetMapping("/allNft")
//    public String AllNft(Model model) {
//
////        List<CommenNftRe> mainResponseAll = nftFindService.findAllList(pageable  , keyword , sort);
////
////        MainPageDto mainPageDto = MainPageDto.builder()
////                .total(mainResponseAll.size())
////                .nftList(mainResponseAll)
////                .build();
////
////        return mainPageDto;
//        return "pages/allNft";
//    }

    @GetMapping("/loadNft")
    public String LoadNft(Model model) {
        return "pages/loadNft";
    }
}
