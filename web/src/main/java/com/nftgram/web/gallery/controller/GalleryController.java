package com.nftgram.web.gallery.controller;


import com.nftgram.core.dto.request.NftMemberRequestDto;
import com.nftgram.web.common.auth.MemberLoginManager;
import com.nftgram.web.common.dto.GalleryDto;
import com.nftgram.web.common.dto.GalleryMemberDto;
import com.nftgram.web.common.dto.response.CommonNftResponse;
import com.nftgram.web.common.service.NftFindService;
import com.nftgram.web.gallery.service.GalleryService;
import com.nftgram.web.member.dto.NftMemberAuthDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class GalleryController {

    private final NftFindService nftFindService;
    private final GalleryService galleryService;
    private final MemberLoginManager memberLoginManager;


    @GetMapping("/gallery")
    public String gallery(Model model, Pageable pageable, String keyword , Long sort) throws ParseException {
        List<CommonNftResponse> nftList = galleryService.findAllNftGallery(pageable);

        GalleryDto galleryDto = galleryService.findAllNFTList(pageable, keyword, sort);

        model.addAttribute("nav_active","explorer");
        model.addAttribute("collection", galleryDto.getCollection());
        model.addAttribute("nftList", galleryDto.getGalleryList());
        model.addAttribute("slideList", galleryDto.getSlideList());

        return "gallery/gallery";
    }

    @GetMapping("gallery/{collection}")
    public String galleryDetail(Model model , @PathVariable("collection") Long collectionId) {

        GalleryDto galleryDto = nftFindService.findByNftCollectionId(collectionId);

        model.addAttribute("nav_active","explorer");
        model.addAttribute("collection",galleryDto.getCollection());
        model.addAttribute("nftList",galleryDto.getGalleryList());
        model.addAttribute("slideList", galleryDto.getSlideList());


        return "gallery/galleryCollection";
    }

//    @GetMapping("/gallery_swiper/{collection}")
//    public String gallerySwiper(Model model , @PathVariable("collection") Long collectionId) {
//
//        GalleryDto galleryDto = nftFindService.findByNftCollectionId(collectionId);
//
//        model.addAttribute("nav_active","explorer");
//        model.addAttribute("collection",galleryDto.getCollection());
//        model.addAttribute("nftList",galleryDto.getGalleryList());
//
//        return "gallery/gallery_swiper";
//    }

    @GetMapping("gallery/myfavorite/{nftMemberUserId}")
    public String myfavorite(@PathVariable("nftMemberUserId") String nftMemberUserId , Model model, Pageable pageable) throws GeneralSecurityException, UnsupportedEncodingException {
        Long memberId = Long.valueOf(0);
        NftMemberAuthDto authDto = memberLoginManager.getInfo();
        if(authDto.getLoginYN().equals("Y")) {
            memberId = authDto.getNftMemberId();

            GalleryMemberDto galleryMemberDto = galleryService.findAllNftGalleryMemberLike(pageable, memberId , nftMemberUserId);

            model.addAttribute("nav_active","myfavorite");
            model.addAttribute("member",galleryMemberDto.getNftMember());
            model.addAttribute("nftMemberUserId",galleryMemberDto.getNftMember().getNftMemberUserId());
            model.addAttribute("sliderList",galleryMemberDto.getNftSliderList());
            model.addAttribute("nftList",galleryMemberDto.getNftResponseList());

            return "gallery/myfavorite";
        } else {
            return "redirect:/auth/login";
        }
    }

    @GetMapping("gallery/mycollection/{nftMemberUserId}")
    public String mycollection(@PathVariable("nftMemberUserId") String nftMemberUserId ,  Model model, Pageable pageable  ) throws GeneralSecurityException, UnsupportedEncodingException {
        Long memberId = Long.valueOf(0);
        NftMemberAuthDto authDto = memberLoginManager.getInfo();
        if(authDto.getLoginYN().equals("Y")) {
            memberId = authDto.getNftMemberId();


            GalleryMemberDto galleryMemberDto = galleryService.findAllNftGalleryMember(pageable, memberId , nftMemberUserId);

            if(galleryMemberDto.getSliderCount() >= 0) {
                model.addAttribute("nav_active","mycollection");
                model.addAttribute("member",galleryMemberDto.getNftMember());
                model.addAttribute("nftList",galleryMemberDto.getNftResponseList());
                model.addAttribute("slideList", galleryMemberDto.getNftSliderList());
                return "gallery/mycollection";
            } else {
                return "gallery/mycollection";
            }

        } else {
            return "redirect:/auth/login";
        }
    }

    @PostMapping(value = "gallery/mycollection/save")
    public String nftMemberUpdate(NftMemberRequestDto update ) throws GeneralSecurityException , UnsupportedEncodingException{
        Long isResult = Long.valueOf(0);
        Long memberId = Long.valueOf(0);
        NftMemberAuthDto authDto = memberLoginManager.getInfo();

        if(authDto.getLoginYN().equals("Y")) {
            memberId = authDto.getNftMemberId();
            isResult = galleryService.nftMemberUpdate(update, memberId);
        } else {
            isResult = Long.valueOf(2); //Error
        }
        return "redirect:/gallery/mycollection";

    }

    @GetMapping("gallery/edit")
    public String editgallery(Model model) {

        model.addAttribute("nav_active", "mycolllection");
        return "gallery/galleryEdit";
    }
}
