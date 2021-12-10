package com.nftgram.web.api.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateLikeCountPlusResponse {
    private boolean loginFlag;

    private boolean likeFlag;

    private Long nftId;

    private Long nftMemberId;

    private Long likeTotalCount;

    @Builder
    public UpdateLikeCountPlusResponse(boolean loginFlag, boolean likeFlag, Long nftId, Long nftMemberId, Long likeTotalCount) {
        this.loginFlag = loginFlag;
        this.likeFlag = likeFlag;
        this.nftId = nftId;
        this.nftMemberId = nftMemberId;
        this.likeTotalCount = likeTotalCount;

    }
}
