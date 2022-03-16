package com.nftgram.admin.memberlist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MemberManageController {

    @GetMapping("/member_list")
    public String nftMebmerList() {

        return "pages/memberList";
    }


    @GetMapping("/member_table")
    public  String nftMemberTable(){

        return "pages/memberList";
    }


}
