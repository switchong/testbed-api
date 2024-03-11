package com.testbed.web.common.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NftFavoriteResponse {

    private Long favId;

    private Long nftId;

    private Long nftMemberId;

    private String assetContractAddress;

    private String tokenId;

    private String activeStatus;

    private String isMine;

    private LocalDateTime createdDate;

    @Builder
    public NftFavoriteResponse(Long favId, Long nftId, Long nftMemberId, String assetContractAddress, String tokenId,
                           String activeStatus, String isMine, LocalDateTime createdDate) {
        if(isMine.isEmpty()) {
            isMine = "N";
        }
        this.favId = favId;
        this.nftId = nftId;
        this.nftMemberId = nftMemberId;
        this.assetContractAddress = assetContractAddress;
        this.tokenId = tokenId;
        this.activeStatus = activeStatus;
        this.isMine = isMine;
        this.createdDate = createdDate;
    }

}
