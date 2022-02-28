package com.nftgram.web.gallery.controller;


import com.nftgram.core.dto.request.NftGalleryRequest;
import com.nftgram.core.dto.request.NftMemberRequestDto;
import com.nftgram.web.common.auth.MemberLoginManager;
import com.nftgram.web.common.dto.GalleryDto;
import com.nftgram.web.common.dto.GalleryMemberDto;
import com.nftgram.web.common.dto.NftGalleryCommonDto;
import com.nftgram.web.common.service.NftFindService;
import com.nftgram.web.gallery.service.GalleryService;
import com.nftgram.web.member.dto.NftMemberAuthDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.text.ParseException;

@Controller
@RequiredArgsConstructor
@Slf4j
public class GalleryController {

    private final NftFindService nftFindService;
    private final GalleryService galleryService;
    private final MemberLoginManager memberLoginManager;


    @GetMapping("/gallery")
    public String gallery(Model model, Pageable pageable, String keyword , Long sort) throws ParseException {

        GalleryDto galleryDto = galleryService.findAllNFTList(pageable, keyword, sort);

        model.addAttribute("nav_active","explorer");
        model.addAttribute("collection", galleryDto.getCollection());

        return "gallery/gallery";
    }

    @GetMapping("/gallery/{collection}")
    public String galleryDetail(Model model ,Pageable pageable, @PathVariable("collection") Long collectionId) {

        GalleryDto galleryDto = nftFindService.findByNftCollectionId(pageable, collectionId);

        model.addAttribute("nav_active","explorer");
        model.addAttribute("collection",galleryDto.getCollection());
        model.addAttribute("nftList",galleryDto.getGalleryList());
        model.addAttribute("slideList", galleryDto.getSlideList());


        return "gallery/galleryCollection";
    }

    @GetMapping("gallery/myfavorite")
    public String myfavorite( String nftMemberUserId , Model model, Pageable pageable) throws GeneralSecurityException, UnsupportedEncodingException {
        Long memberId = Long.valueOf(0);
        NftMemberAuthDto authDto = memberLoginManager.getInfo();
        if(authDto.getLoginYN().equals("Y")) {
            memberId = authDto.getNftMemberId();

            GalleryMemberDto galleryMemberDto = galleryService.findAllNftGalleryMemberLike(pageable, memberId );

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

    @GetMapping("gallery/mycollection")
    public String mycollection( Model model, Pageable pageable ) throws GeneralSecurityException, UnsupportedEncodingException {
        Long memberId = Long.valueOf(0);
        NftMemberAuthDto authDto = memberLoginManager.getInfo();
        if(authDto.getLoginYN().equals("Y")) {
            memberId = authDto.getNftMemberId();


            GalleryMemberDto galleryMemberDto = galleryService.findAllNftGalleryMember(pageable, memberId );

            model.addAttribute("nav_active","mycollection");
            model.addAttribute("member",galleryMemberDto.getNftMember());
            model.addAttribute("nftMemberUserId",galleryMemberDto.getNftMember().getNftMemberUserId());
            model.addAttribute("nftList",galleryMemberDto.getNftResponseList());
            model.addAttribute("slideList", galleryMemberDto.getNftSliderList());

            return "gallery/mycollection";

        } else {
            return "redirect:/auth/login";
        }
    }

    @PostMapping(value = "/gallery/mycollection/save")
    public String nftMemberUpdate(@Valid NftMemberRequestDto update , Errors error , Model model) throws GeneralSecurityException , UnsupportedEncodingException{
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


    @PostMapping("/username/check")
    public ResponseEntity<Boolean> checkUsernameDuplicate(@RequestParam(name = "id") String username){
        return  ResponseEntity.ok(galleryService.checkUsername(username));
    }

    @GetMapping("/gallery/edit")
    public String editgallery(Model model, Pageable pageable) throws GeneralSecurityException, UnsupportedEncodingException, ParseException {
        Long memberId = Long.valueOf(0);
        NftMemberAuthDto authDto = memberLoginManager.getInfo();

        NftGalleryRequest nftGalleryRequest = new NftGalleryRequest();

        model.addAttribute("nav_active", "mycolllection");

        // Login Manager Check
        if(authDto.getLoginYN().equals("Y")) {
            memberId = authDto.getNftMemberId();

            nftGalleryRequest.setPageType("edit");
            nftGalleryRequest.setMemberId(memberId);

            NftGalleryCommonDto nftGalleryCommonDto = nftFindService.nftGalleryCommonData(pageable, nftGalleryRequest);

            if(nftGalleryCommonDto.getSliderCount() > 0) {
                model.addAttribute("nav_active","mycollection");
                model.addAttribute("member",nftGalleryCommonDto.getMember());
                model.addAttribute("total",nftGalleryCommonDto.getTotal());
                model.addAttribute("nftList",nftGalleryCommonDto.getNftList());
                model.addAttribute("slideList", nftGalleryCommonDto.getNftSliderList());

                return "gallery/galleryEdit";
            } else {
                model.addAttribute("message","NFT is No Data!!");

                return "error/not_found";
            }


        } else {
            model.addAttribute("message","The wrong Approach!!");

            return "error/not_found";
        }
    }
}
