package com.nftgram.api.controller;


import com.nftgram.api.service.response.ResponseService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"2 . User"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class NftMemberController {



    private final ResponseService responseService;



}
