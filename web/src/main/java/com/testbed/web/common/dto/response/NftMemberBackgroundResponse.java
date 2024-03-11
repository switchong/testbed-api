package com.testbed.web.common.dto.response;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NftMemberBackgroundResponse {
    private CommonNftResponse nftInfo;

    private CommonNftResponse nftFrame;

    @Builder
    public NftMemberBackgroundResponse(CommonNftResponse nftInfo, CommonNftResponse nftFrame) {
        this.nftInfo = nftInfo;
        this.nftFrame = nftFrame;
    }
}
