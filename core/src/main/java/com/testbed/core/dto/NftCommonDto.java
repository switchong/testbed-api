package com.testbed.core.dto;

import com.testbed.core.domain.nftgram.Nft;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NftCommonDto {
    private Long totals;
    private Long offset;
    private Long limit;
    private List<Nft> nftList = new ArrayList<>();
}
