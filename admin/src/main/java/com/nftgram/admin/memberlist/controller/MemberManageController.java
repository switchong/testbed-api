package com.nftgram.admin.memberlist.controller;

import com.nftgram.admin.memberlist.dto.request.NftMemberSearchRequest;
import com.nftgram.admin.memberlist.dto.response.NftMemberManagerResponse;
import com.nftgram.admin.memberlist.service.MemberManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberManageController {




    private final MemberManagerService memberManagerService;




    @GetMapping("/member_list")
    public String nftMebmerList(@Valid NftMemberSearchRequest request , Model model , String keyword) {

        NftMemberManagerResponse nftMemberManagerResponse = memberManagerService.memberListquery(request, keyword);

        model.addAttribute(nftMemberManagerResponse);
        return "pages/memberList";
    }





}
