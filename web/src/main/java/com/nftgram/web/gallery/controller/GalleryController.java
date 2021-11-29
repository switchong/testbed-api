package com.nftgram.web.gallery.controller;


import com.nftgram.core.domain.nftgram.Nft;
import com.nftgram.web.gallery.service.GalleryService;
import com.nftgram.web.main.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GalleryController {

    private final MainService mainService;
    private final GalleryService galleryService;


    @GetMapping("/gallery")
    public String gallery(){


        return "redirect:/index";
    }

    @GetMapping("/gallery/{collection}")
    public String galleryDetail(Model model , @PathVariable("collection") String collection) {

//        List<Nft> NftInfo = galleryService.getNftInfo(collection);
        List<Nft> NftInfo = galleryService.NftCollectionList(collection);

        model.addAttribute("nftInfo",NftInfo);

        return "gallery/gallery";
    }
}
