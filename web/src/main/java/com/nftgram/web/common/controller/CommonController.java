package com.nftgram.web.common.controller;

import com.nftgram.core.repository.NftRepository;
import com.nftgram.web.common.dto.request.CommonLikeUpdateRequest;
import com.nftgram.web.common.dto.request.CommonViewCountUpdateRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonController {

    private NftRepository nftRepository;

    @PostMapping(value = "/common/upview", produces = "application/json")
    public boolean updateView(CommonViewCountUpdateRequest viewCountUpdateRequest) {


        return true;
    }

    @PostMapping(value = "/common/uplike", produces = "application/json")
    public boolean saveLike(CommonLikeUpdateRequest likeUpdateRequest) {

        return true;
    }

}
