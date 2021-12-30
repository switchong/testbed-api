package com.nftgram.web.main.controller;

import com.nftgram.web.common.dto.response.CommonNftResponse;
import com.nftgram.web.common.service.NftFindService;
import com.nftgram.web.main.dto.service.MainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class MainController {

    private final MainService mainService;
    private final NftFindService nftFindService;

    @GetMapping("/")
    public String Main(Model model, Pageable pageable , String keyword, Long sort ) throws GeneralSecurityException, UnsupportedEncodingException, ParseException {

        if (sort == null){
            sort = Long.valueOf(0);
        }

        List<CommonNftResponse> mainResponseAll = nftFindService.findAllList(pageable , keyword ,sort );
//        model.addAttribute("nftList",mainResponseAll);
        return "index";
    }
}
