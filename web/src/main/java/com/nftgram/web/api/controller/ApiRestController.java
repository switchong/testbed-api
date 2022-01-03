package com.nftgram.web.api.controller;

import com.nftgram.web.api.dto.MainPageDto;
import com.nftgram.web.api.dto.MemberWalletDto;
import com.nftgram.web.api.dto.request.GetNftOneRequest;
import com.nftgram.web.api.dto.request.MemberNftRequest;
import com.nftgram.web.api.dto.request.UpdateLikeCountRequest;
import com.nftgram.web.api.dto.request.UpdateViewCountRequest;
import com.nftgram.web.api.dto.response.GetNftOneResponse;
import com.nftgram.web.api.dto.response.MemberNftResponse;
import com.nftgram.web.api.dto.response.MemberWalletResponses;
import com.nftgram.web.api.dto.response.UpdateLikeCountResponse;
import com.nftgram.web.api.service.ApiRestService;
import com.nftgram.web.common.auth.MemberLoginManager;
import com.nftgram.web.common.dto.NftCommentDto;
import com.nftgram.web.common.dto.NftPropertiesGroupDto;
import com.nftgram.web.common.dto.request.NftCommentRequest;
import com.nftgram.web.common.dto.request.NftCommentSaveRequest;
import com.nftgram.web.common.dto.response.CommonNftResponse;
import com.nftgram.web.common.service.NftCommentService;
import com.nftgram.web.common.service.NftFindService;
import com.nftgram.web.member.dto.NftMemberAuthDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api")
@Slf4j
public class ApiRestController {

    private final ApiRestService apiRestService;
    private final NftFindService nftFindService;
    private final NftCommentService commentService;
    private final MemberLoginManager memberLoginManager;


    @GetMapping("/main/page")
    public MainPageDto getMainPage(Pageable pageable, String keyword , Long sort ) throws ParseException {
        List<CommonNftResponse> mainResponseAll = nftFindService.findAllList(pageable  , keyword , sort);


        MainPageDto mainPageDto = MainPageDto.builder()
                .total(mainResponseAll.size())
                .nftList(mainResponseAll)

                .build();

        return mainPageDto;
    }

    @PostMapping("/nft")
    public GetNftOneResponse getNftOneResponse(GetNftOneRequest getNftOneRequest) throws GeneralSecurityException, UnsupportedEncodingException {
        Long memberId = Long.valueOf(0);
        NftMemberAuthDto authDto = memberLoginManager.getInfo();
        if(authDto.getLoginYN().equals("Y")) {
            memberId = authDto.getNftMemberId();
        }
        GetNftOneResponse getNftOneResponse = apiRestService.getNftOneResponse(getNftOneRequest.getNftId(), memberId);

        return getNftOneResponse;
    }

    @PostMapping("/nft/property")
    public NftPropertiesGroupDto nftPropGroupResponse() {
        NftPropertiesGroupDto propGroupResponse = apiRestService.nftPropertiesCountDto();

        return propGroupResponse;
    }

    @PostMapping(value = "/upview", produces = "application/json")
    public Long updateViewCount(UpdateViewCountRequest viewCountUpdateRequest) {
        Long viewTotalCount = apiRestService.updateViewCount(viewCountUpdateRequest.getNftId());

        return viewTotalCount;
    }

    @PostMapping(value = "/uplike", produces = "application/json")
    public UpdateLikeCountResponse updateLikeCountResponse(UpdateLikeCountRequest likeUpdateRequest) throws GeneralSecurityException, UnsupportedEncodingException {
        Long likeTotal = Long.valueOf(0);
        UpdateLikeCountResponse updateLikeCountResponse = UpdateLikeCountResponse.builder()
                .loginFlag("N")
                .likeFlag("N")
                .nftId(likeUpdateRequest.getNftId())
                .likeTotalCount(likeTotal)
                .build();

        NftMemberAuthDto authDto = memberLoginManager.getInfo();
        if(authDto.getLoginYN().equals("Y")) {
            updateLikeCountResponse = apiRestService.updateLikeCount(likeUpdateRequest.getNftId(), authDto.getNftMemberId());
        }

        return updateLikeCountResponse;
    }

    @PostMapping(value = "/member/nft", produces = "application/json")
    public MemberNftResponse GetMemberNftInfo(MemberNftRequest memberNftRequest) {
        MemberNftResponse memberNftResponse;
        return null;
    }

    @PostMapping(value = "/member/wallet/list", produces = "application/json")
    public MemberWalletDto memberWalletResponse() throws GeneralSecurityException, UnsupportedEncodingException {
        String loginFlag = "N";
        String walletFlag = "N";
        Long memberId = Long.valueOf(0);
        List<MemberWalletResponses> memberWalletResponses = new ArrayList<>();

        NftMemberAuthDto authDto = memberLoginManager.getInfo();
        if(authDto.getLoginYN().equals("Y")) {
            loginFlag = "Y";
            memberId = authDto.getNftMemberId();

            memberWalletResponses = apiRestService.memberWalletResponses(memberId, loginFlag);
            if(memberWalletResponses.size() > 0) {
                walletFlag = "Y";
            }
        }

        MemberWalletDto memberWalletDto = MemberWalletDto.builder()
                .loginFlag(loginFlag)
                .walletFlag(walletFlag)
                .memberId(memberId)
                .memberWalletResponsesList(memberWalletResponses)
                .build();

        return memberWalletDto;
    }

    @PostMapping(value = "/member/wallet/save", produces = "application/json")
    public Long memberWalletSave(@RequestParam("address") String walletContractAddress) throws GeneralSecurityException, UnsupportedEncodingException {
        Long isResult = Long.valueOf(0);
        Long memberId = Long.valueOf(0);
        NftMemberAuthDto authDto = memberLoginManager.getInfo();
        if(authDto.getLoginYN().equals("Y")) {
            memberId = authDto.getNftMemberId();
            isResult = apiRestService.memberWalletSave(walletContractAddress, memberId);
        } else {
            isResult = Long.valueOf(2); //Error
        }

        return isResult;

    }

    @PostMapping(value = "/member/wallet/delete", produces = "application/json")
    public Long memberWalletDeleteStatus(@RequestParam("wid") Long walletId) throws GeneralSecurityException, UnsupportedEncodingException {
        Long isResult = Long.valueOf(0);
        Long memberId = Long.valueOf(0);
        NftMemberAuthDto authDto = memberLoginManager.getInfo();
        if(authDto.getLoginYN().equals("Y")) {
            memberId = authDto.getNftMemberId();
            isResult = apiRestService.memberWalletDeleteStatus(walletId, memberId);
        } else {
            isResult = Long.valueOf(2); //Error
        }

        return isResult;

    }

    @PostMapping(value = "/comment/list", produces = "application/json")
    public NftCommentDto nftCommentList(NftCommentRequest commentRequest) throws GeneralSecurityException, UnsupportedEncodingException {
        Long memberId = Long.valueOf(0);
        NftMemberAuthDto authDto = memberLoginManager.getInfo();
        if(authDto.getLoginYN().equals("Y")) {
            memberId = authDto.getNftMemberId();
        }
        NftCommentDto isResult = commentService.commentResponseList(commentRequest, memberId);

        return isResult;
    }

    @PostMapping(value = "/comment/save", produces = "application/json")
    public Long nftCommentSave(NftCommentSaveRequest nftCommentSaveRequest) throws GeneralSecurityException, UnsupportedEncodingException {
        Long isResult = Long.valueOf(0);
        Long memberId = Long.valueOf(0);
        NftMemberAuthDto authDto = memberLoginManager.getInfo();
        if(authDto.getLoginYN().equals("Y")) {
            memberId = authDto.getNftMemberId();

            isResult = commentService.commentSave(nftCommentSaveRequest, memberId);
        } else {
            isResult = Long.valueOf(2);
        }



        return isResult;
    }
}
