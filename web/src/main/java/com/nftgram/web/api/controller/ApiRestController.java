package com.nftgram.web.api.controller;

import com.nftgram.web.api.dto.MainPageDto;
import com.nftgram.web.api.dto.request.MemberNftRequest;
import com.nftgram.web.api.dto.request.UpdateLikeCountRequest;
import com.nftgram.web.api.dto.request.UpdateViewCountRequest;
import com.nftgram.web.api.dto.response.MemberNftResponse;
import com.nftgram.web.api.dto.response.UpdateLikeCountPlusResponse;
import com.nftgram.web.api.service.ApiRestService;
import com.nftgram.web.common.auth.MemberLoginManager;
import com.nftgram.web.common.dto.request.NftCommentRequest;
import com.nftgram.web.common.dto.response.CommonNftResponse;
import com.nftgram.web.common.dto.response.NftCommentResponse;
import com.nftgram.web.common.service.NftCommentService;
import com.nftgram.web.common.service.NftFindService;
import com.nftgram.web.member.dto.NftMemberAuthDto;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("api")
public class ApiRestController {

    private final ApiRestService apiRestService;
    private final NftFindService nftFindService;
    private final NftCommentService commentService;
    private final MemberLoginManager memberLoginManager;

    public ApiRestController(ApiRestService apiRestService, NftFindService nftFindService, NftCommentService commentService, MemberLoginManager memberLoginManager) {
        this.apiRestService = apiRestService;
        this.nftFindService = nftFindService;
        this.commentService = commentService;
        this.memberLoginManager = memberLoginManager;
    }

    @GetMapping("/main/page")
    public MainPageDto getMainPage(Pageable pageable) throws ParseException {
        List<CommonNftResponse> mainResponseAll = nftFindService.findAllList(pageable);
        MainPageDto mainPageDto = new MainPageDto(mainResponseAll, mainResponseAll.size());

        return mainPageDto;
    }

    @PostMapping(value = "/upview", produces = "application/json")
    public Long updateViewCount(UpdateViewCountRequest viewCountUpdateRequest) {
        Long viewTotalCount = apiRestService.updateViewCount(viewCountUpdateRequest.getNftId());

        return viewTotalCount;
    }

    @PostMapping(value = "/uplike", produces = "application/json")
    public UpdateLikeCountPlusResponse updateLikeCountPlusResponse(UpdateLikeCountRequest likeUpdateRequest) throws GeneralSecurityException, UnsupportedEncodingException {
        boolean loginFlag = false;
        boolean likeFlag = false;
        Long likeTotal = Long.valueOf(0);

        NftMemberAuthDto authDto = memberLoginManager.getInfo();
        if(authDto.getLoginYN().equals("Y")) {
            loginFlag = true;
        }

        if(loginFlag == true) {
            Long result = apiRestService.updateLikeCount(likeUpdateRequest.getNftId(), authDto.getNftMemberId());
            if(result > 0) {
                likeFlag = true;
            }
        }
        UpdateLikeCountPlusResponse updateLikeCountPlusResponse = UpdateLikeCountPlusResponse.builder()
                .loginFlag(loginFlag)
                .likeFlag(likeFlag)
                .nftId(likeUpdateRequest.getNftId())
                .nftMemberId(authDto.getNftMemberId())
                .likeTotalCount(likeTotal)
                .build();

        return updateLikeCountPlusResponse;
    }

    @PostMapping(value = "/member/nft", produces = "application/json")
    public MemberNftResponse GetMemberNftInfo(MemberNftRequest memberNftRequest) {
        MemberNftResponse memberNftResponse;
        return null;
    }

    @PostMapping(value = "/comment/save", produces = "application/json")
    public boolean NftCommentSave(NftCommentRequest commentRequest) {
        boolean isResult = false;
        NftCommentResponse commentResponse;
        return isResult;
    }
}
