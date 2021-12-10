package com.nftgram.web.common.dto.response;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NftLikeResponse {

    private Long likeId;

    private Long nftId;

    private Long nftMemberId;

    private String assetContractAddress;

    private String tokenId;

    private String activeStatus;

    private String isMine;

    private LocalDateTime createdDate;

    @Builder
    public NftLikeResponse(Long likeId, Long nftId, Long nftMemberId, String assetContractAddress, String tokenId,
                              String activeStatus, String isMine, LocalDateTime createdDate) {
        if(isMine.isEmpty()) {
            isMine = "N";
        }
        this.likeId = likeId;
        this.nftId = nftId;
        this.nftMemberId = nftMemberId;
        this.assetContractAddress = assetContractAddress;
        this.tokenId = tokenId;
        this.activeStatus = activeStatus;
        this.isMine = isMine;
        this.createdDate = createdDate;
    }

}