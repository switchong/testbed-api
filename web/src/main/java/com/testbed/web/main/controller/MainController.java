package com.testbed.web.main.controller;

import com.testbed.web.common.service.NftFindService;
import com.testbed.web.main.dto.service.MainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {

    private final MainService mainService;
    private final NftFindService nftFindService;

//    @GetMapping("/")
//    public String Main(Model model, Pageable pageable , String keyword, Long sort ) throws GeneralSecurityException, UnsupportedEncodingException, ParseException {
//
//        if (sort == null){
//            sort = Long.valueOf(0);
//        }
//
//        model.addAttribute("nav_active","explorer");
//
//        List<CommonNftResponse> mainResponseAll = nftFindService.findAllList(pageable , keyword ,sort );
//        return "index";
//    }
}
