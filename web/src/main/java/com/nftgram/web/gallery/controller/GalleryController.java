package com.nftgram.web.gallery.controller;


import com.nftgram.web.common.auth.MemberLoginManager;
import com.nftgram.web.common.dto.GalleryDto;
import com.nftgram.web.common.dto.GalleryMemberDto;
import com.nftgram.web.common.dto.response.CommonNftResponse;
import com.nftgram.web.common.service.NftFindService;
import com.nftgram.web.gallery.service.GalleryService;
import com.nftgram.web.member.dto.NftMemberAuthDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class GalleryController {

    private final NftFindService nftFindService;
    private final GalleryService galleryService;
    private final MemberLoginManager memberLoginManager;


    @GetMapping("/gallery")
    public String gallery(Model model, Pageable pageable){
        List<CommonNftResponse> nftList = galleryService.findAllNftGallery(pageable);

        model.addAttribute("nftList",nftList);
        model.addAttribute("nav_active","explorer");

        return "gallery/gallery";
    }

    @GetMapping("/gallery/{collection}")
    public String galleryDetail(Model model , @PathVariable("collection") Long collectionId) {

        GalleryDto galleryDto = nftFindService.findByNftCollectionId(collectionId);

        model.addAttribute("collection",galleryDto.getCollection());
        model.addAttribute("nftList",galleryDto.getGalleryList());
        model.addAttribute("nav_active","explorer");

        return "gallery/gallery";
    }

    @GetMapping("/gallery_swiper/{collection}")
    public String gallerySwiper(Model model , @PathVariable("collection") Long collectionId) {

        GalleryDto galleryDto = nftFindService.findByNftCollectionId(collectionId);

        model.addAttribute("collection",galleryDto.getCollection());
        model.addAttribute("nftList",galleryDto.getGalleryList());
        model.addAttribute("nav_active","explorer");

        return "gallery/gallery_swiper";
    }

    @GetMapping("/gallery/myfavorite")
    public String myfavorite(Model model, Pageable pageable) throws GeneralSecurityException, UnsupportedEncodingException {
        Long memberId = Long.valueOf(0);
        NftMemberAuthDto authDto = memberLoginManager.getInfo();
        if(authDto.getLoginYN().equals("Y")) {
            memberId = authDto.getNftMemberId();

            GalleryMemberDto galleryMemberDto = galleryService.findAllNftGalleryMemberLike(pageable, memberId);

            model.addAttribute("member",galleryMemberDto.getNftMember());
            model.addAttribute("nftList",galleryMemberDto.getNftResponseList());
            model.addAttribute("nav_active","myfavorite");

            return "gallery/myfavorite";
        } else {
            return "redirect:/auth/login";
        }
    }

    @GetMapping("/gallery/mycollection")
    public String mycollection(Model model, Pageable pageable) throws GeneralSecurityException, UnsupportedEncodingException {
        Long memberId = Long.valueOf(0);
        NftMemberAuthDto authDto = memberLoginManager.getInfo();

        if(authDto.getLoginYN().equals("Y")) {
            memberId = authDto.getNftMemberId();

            GalleryMemberDto galleryMemberDto = galleryService.findAllNftGalleryMember(pageable, memberId);

            model.addAttribute("member",galleryMemberDto.getNftMember());
            model.addAttribute("nftList",galleryMemberDto.getNftResponseList());
            model.addAttribute("nav_active","mycollection");

            return "gallery/mycollection";
        } else {
            return "redirect:/auth/login";
        }
    }

}
