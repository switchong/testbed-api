package com.nftgram.admin.carenft.controller;


import com.nftgram.admin.carenft.dto.NftPagingResponse;
import com.nftgram.admin.carenft.dto.request.NftSearchRequest;
import com.nftgram.admin.carenft.service.NftInquiryService;


import com.nftgram.core.domain.common.value.ActiveStatus;
import lombok.RequiredArgsConstructor;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class AllNftController {


    private final NftInquiryService nftInquiryService;

    @GetMapping("/allNft")
    public String AllNft(@Valid NftSearchRequest request , Model model  , String keyword)  {


        NftPagingResponse nftPagingResponse = nftInquiryService.nftListquery(request , keyword);

        model.addAttribute(nftPagingResponse);

        return "pages/allNft";
    }
    @ResponseBody
    @PostMapping("/admin/nftList/{id}")
    public ResponseEntity<String> orderStatusChangePage(@PathVariable Long id, @RequestParam ActiveStatus status) {
        nftInquiryService.changeOrderStatus(id, status);


        return  new ResponseEntity<>("sucess" , HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("/admin/deleteNft")
    public List<String> deleteNft(@RequestBody List<String> boardArray){
        nftInquiryService.deleteBoard(boardArray);

        return boardArray;
    }
}
