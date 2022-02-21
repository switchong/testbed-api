package com.nftgram.core.dto;

import com.nftgram.core.domain.nftgram.Nft;
import com.nftgram.core.domain.nftgram.NftMemberBackground;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class NftMemberBgDto {
    private Nft nft;
    private NftMemberBackground nftMemberBackground;

    @Builder
    public NftMemberBgDto(Nft nft, NftMemberBackground nftMemberBackground) {
        this.nft = nft;
        this.nftMemberBackground = nftMemberBackground;
    }
}
