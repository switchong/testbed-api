package com.nftgram.core.dto;

import com.nftgram.core.domain.nftgram.Nft;
import com.nftgram.core.domain.nftgram.NftAsset;
import com.nftgram.core.domain.nftgram.NftCollection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NftOneJoinDto {
    private Nft nft;
    private NftAsset nftAsset;
    private NftCollection nftCollection;
}
