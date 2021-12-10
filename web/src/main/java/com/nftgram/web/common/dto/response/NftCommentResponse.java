package com.nftgram.web.common.dto.response;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NftCommentResponse {

    private Long commId;

    private Long nftId;

    private Long nftMemberId;

    private String assetContractAddress;

    private String tokenId;

    private Long depth;

    private Long parent;

    private String title;

    private String comment;

    private String activeStatus;

    private String isMine;

    private LocalDateTime createdDate;

    @Builder
    public NftCommentResponse(Long commId, Long nftId, Long nftMemberId, String assetContractAddress, String tokenId,
                              Long depth, Long parent, String title, String comment, String activeStatus, String isMine, LocalDateTime createdDate) {
        if(isMine.isEmpty()) {
            isMine = "N";
        }
        this.commId = commId;
        this.nftId = nftId;
        this.nftMemberId = nftMemberId;
        this.assetContractAddress = assetContractAddress;
        this.tokenId = tokenId;
        this.depth = depth;
        this.parent = parent;
        this.title = title;
        this.comment = comment;
        this.activeStatus = activeStatus;
        this.isMine = isMine;
        this.createdDate = createdDate;
    }

}
