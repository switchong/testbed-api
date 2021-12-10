package com.nftgram.web.gallery.controller;


import com.nftgram.web.common.dto.response.CommonNftResponse;
import com.nftgram.web.common.service.NftFindService;
import com.nftgram.web.gallery.service.GalleryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GalleryController {

    private final NftFindService nftFindService;
    private final GalleryService galleryService;


    @GetMapping("/gallery")
    public String gallery(Model model, Pageable pageable){
        List<CommonNftResponse> NftInfo = galleryService.findAllNftGallery(pageable);

        model.addAttribute("nftList",NftInfo);

        return "gallery/gallery";
    }

    @GetMapping("/gallery/{collection}")
    public String galleryDetail(Model model , @PathVariable("collection") Long collectionId) {

        List<CommonNftResponse> NftInfo = nftFindService.findByNftCollectionId(collectionId);

        model.addAttribute("nftInfo",NftInfo);

        return "gallery/gallery";
    }

    @GetMapping("/gallery/myfavorite")
    public String myfavorite(Model model, Pageable pageable) {

        List<CommonNftResponse> NftInfo = galleryService.findAllNftGallery(pageable);

        model.addAttribute("nftInfo",NftInfo);

        return "gallery/myfavorite";
    }

    @GetMapping("/gallery/mycollection")
    public String mycollection(Model model, Pageable pageable) {

        List<CommonNftResponse> NftInfo = galleryService.findAllNftGallery(pageable);

        model.addAttribute("nftInfo",NftInfo);

        return "gallery/mycollection";
    }
}
