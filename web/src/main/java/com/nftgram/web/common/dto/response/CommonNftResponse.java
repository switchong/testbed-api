package com.nftgram.web.common.dto.response;

import com.nftgram.core.domain.nftgram.value.MarketType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
public class CommonNftResponse  implements  Comparable<CommonNftResponse>{

    private Boolean findFlag;

    private Long nftId;

    private String name;

    private String username;

    private Long likeCount;

    private Long favoriteCount;

    private Long viewCount;

    private String marketLink;

    private String userImageUrl;

    private String nftImageUrl;

    private String nftCollectionName;

    private Long nftCollectionId;

    private String assetContractAddress;

    private String tokenId;

    private String tagType;

    private Long frameNftId;

    private String userUrl;

    private LocalDateTime localDate;

    private String description;

    private MarketType marketType;

    private Long marketId;

    private String collectionName;

    private Long nftAssetId;

    private LocalDateTime createdDate;


    @Builder
    public CommonNftResponse(Long nftId, String name, String username, Long likeCount, Long favoriteCount, Long viewCount, String marketLink, String userImageUrl, String nftImageUrl,
                             String nftCollectionName, Long nftCollectionId, String assetContractAddress, String tokenId, String tagType, Long frameNftId, String userUrl, LocalDateTime localDate,
                             String description, MarketType marketType, Long marketId, String collectionName, Long nftAssetId, LocalDateTime createdDate) {
        this.nftId = nftId;
        this.name = name;
        this.username = username;
        this.likeCount = likeCount;
        this.favoriteCount = favoriteCount;
        this.viewCount = viewCount;
        this.marketLink = marketLink;
        this.userImageUrl = userImageUrl;
        this.nftImageUrl = nftImageUrl;
        this.nftCollectionName = nftCollectionName;
        this.nftCollectionId = nftCollectionId;
        this.assetContractAddress = assetContractAddress;
        this.tokenId = tokenId;
        this.tagType = tagType;
        this.frameNftId = frameNftId;
        this.userUrl = userUrl;
        this.localDate = localDate;

        this.description = description;
        this.marketType = marketType;
        this.marketId = marketId;
        this.collectionName = collectionName;
        this.nftAssetId = nftAssetId;
        this.createdDate = createdDate;
    }


    @Override
    public int compareTo(CommonNftResponse o) {
        return 0;
    }


}
