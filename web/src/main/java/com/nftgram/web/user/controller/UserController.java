package com.nftgram.web.user.controller;

import com.nftgram.web.common.auth.MemberLoginManager;
import com.nftgram.web.common.dto.GalleryMemberDto;
import com.nftgram.web.common.service.NftFindService;
import com.nftgram.web.gallery.service.GalleryService;
import com.nftgram.web.member.dto.NftMemberAuthDto;
import com.nftgram.web.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.text.ParseException;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user")
public class UserController {

    private final NftFindService nftFindService;
    private final GalleryService galleryService;
    private final UserService userService;
    private final MemberLoginManager memberLoginManager;

    @GetMapping("/{member_id}")
    public String username(Model model, Pageable pageable, String keyword , Long sort, @PathVariable("member_id") Long url_member_id) throws GeneralSecurityException, UnsupportedEncodingException {
        Long memberId = Long.valueOf(0);
        NftMemberAuthDto authDto = memberLoginManager.getInfo();

        memberId = authDto.getNftMemberId();

        GalleryMemberDto galleryMemberDto = userService.findNftUsername(pageable, memberId, url_member_id);

        model.addAttribute("nav_active","");
        if(galleryMemberDto.getSliderCount() >= 0) {
            model.addAttribute("member",galleryMemberDto.getNftMember());
            model.addAttribute("sliderList",galleryMemberDto.getNftSliderList());
            model.addAttribute("nftList",galleryMemberDto.getNftResponseList());

            return "user/username";
        } else {
            model.addAttribute("message","Not Found User!!");

            return "error/not_found";
        }

    }

    @GetMapping("/address/{address}")
    public String userAddress(Model model, Pageable pageable, String keyword , Long sort, @PathVariable("address") String address) throws ParseException, GeneralSecurityException, UnsupportedEncodingException {
        Long memberId = Long.valueOf(0);
        NftMemberAuthDto authDto = memberLoginManager.getInfo();

        memberId = authDto.getNftMemberId();

        GalleryMemberDto galleryMemberDto = userService.findNftMemberAddress(pageable, memberId, address);

        model.addAttribute("nav_active","");
        if(galleryMemberDto.getNftMember() != null) {
            model.addAttribute("member",galleryMemberDto.getNftMember());
            model.addAttribute("sliderList",galleryMemberDto.getNftSliderList());
            model.addAttribute("nftList",galleryMemberDto.getNftResponseList());

            return "user/address";
        } else {
            model.addAttribute("message","Not Found Address!!");

            return "error/not_found";
        }
    }
}
