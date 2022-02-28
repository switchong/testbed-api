package com.nftgram.web.common.dto.response;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NftMemberBackgroundResponse {
    private CommonNftResponse nft1;
    private CommonNftResponse nft2;
    private CommonNftResponse nft3;


    private CommonNftResponse nftFrame1;
    private CommonNftResponse nftFrame2;
    private CommonNftResponse nftFrame3;

    @Builder
    public NftMemberBackgroundResponse(CommonNftResponse bgNft, CommonNftResponse nft1, CommonNftResponse nft2, CommonNftResponse nft3
            ,CommonNftResponse nftFrame1, CommonNftResponse nftFrame2, CommonNftResponse nftFrame3) {
        this.nft1 = nft1;
        this.nft2 = nft2;
        this.nft3 = nft3;
        this.nftFrame1 = nftFrame1;
        this.nftFrame2 = nftFrame2;
        this.nftFrame3 = nftFrame3;
    }
}
