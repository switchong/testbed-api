package com.nftgram.web.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class GalleryController {


    @GetMapping("/gallery")
    public String gallery(){

        return "gallery/gallery";
    }
}
