package com.testbed.core.dto;

import com.testbed.core.domain.nftgram.Nft;
import com.testbed.core.domain.nftgram.NftAsset;
import com.testbed.core.domain.nftgram.NftCollection;
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
