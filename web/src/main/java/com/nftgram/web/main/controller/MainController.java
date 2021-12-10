package com.nftgram.web.main.controller;

import com.nftgram.web.common.dto.response.CommonNftResponse;
import com.nftgram.web.common.service.NftFindService;
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
    private final NftFindService nftFindService;

    @GetMapping("/")
    public String Main(Model model, Pageable pageable) throws GeneralSecurityException, UnsupportedEncodingException, ParseException {
        List<CommonNftResponse> mainResponseAll = nftFindService.findAllList(pageable);

        model.addAttribute("nftList",mainResponseAll);

        return "index";
    }
}
