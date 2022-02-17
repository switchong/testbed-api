package com.nftgram.core.dto;

import com.nftgram.core.domain.nftgram.Nft;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class NftCommonDto {
    private long totals;
    private long offset;
    private long limit;
    private List<Nft> nftList;
    private List<Nft> nftLists;

    @Builder
    public NftCommonDto(long totals, long offset, long limit, List<Nft> nftList, List<Nft> nftLists) {
        this.totals = totals;
        this.offset = offset;
        this.limit = limit;
        this.nftList = nftList;
        this.nftLists = nftLists;
    }
}
