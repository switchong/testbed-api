package com.nftgram.admin.carenft.controller;


import com.nftgram.admin.carenft.dto.NftPagingResponse;
import com.nftgram.admin.carenft.dto.request.NftSearchRequest;
import com.nftgram.admin.carenft.service.NftInquiryService;


import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.Valid;


@Controller
@RequiredArgsConstructor
public class AllNftController {


    private final NftInquiryService nftInquiryService;

    @GetMapping("/allNft")
    public String AllNft(@Valid NftSearchRequest request , Model model )  {


        NftPagingResponse nftPagingResponse = nftInquiryService.nftListquery(request);

        model.addAttribute(nftPagingResponse);
        return "pages/allNft";
    }


}
