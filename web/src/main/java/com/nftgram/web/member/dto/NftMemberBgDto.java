package com.nftgram.web.member.dto;

import com.nftgram.core.domain.nftgram.Nft;
import com.nftgram.core.domain.nftgram.NftAsset;
import com.nftgram.core.domain.nftgram.NftCollection;
import com.nftgram.core.domain.nftgram.NftMemberBackground;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NftMemberBgDto {
    private NftMemberBackground nftMemberBackground;
    private Nft nft;
    private NftAsset nftAsset;
    private NftCollection nftCollection;
    private Nft bgNft1;
    private Nft bgNft2;
    private Nft bgNft3;

    @Builder
    public NftMemberBgDto(Nft nft, NftAsset nftAsset, NftCollection nftCollection, NftMemberBackground nftMemberBackground,
                          Nft bgNft1, Nft bgNft2, Nft bgNft3) {
        this.nftMemberBackground = nftMemberBackground;
        this.nft = nft;
        this.nftAsset = nftAsset;
        this.nftCollection = nftCollection;
        this.bgNft1 = bgNft1;
        this.bgNft2 = bgNft2;
        this.bgNft3 = bgNft3;
    }
}

