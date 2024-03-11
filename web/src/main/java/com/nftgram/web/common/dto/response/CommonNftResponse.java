package com.nftgram.web.common.dto.response;

import com.testbed.core.domain.nftgram.value.MarketType;
import lombok.*;

import java.time.LocalDateTime;

/**
 * class GetNftOneResponse 파일에도 추가
 */
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

    private String nftImagePreviewUrl;

    private String nftVideoUrl;

    private String nftCollectionName;

    private Long nftCollectionId;

    private String assetContractAddress;

    private String tokenId;

    private String tagType;

    private Long frameNftId;

    private Long orderSeq;

    private Long backgroundSeq;

    private String userUrl;

    private LocalDateTime localDate;

    private String description;

    private MarketType marketType;

    private Long marketId;

    private String collectionName;

    private Long assetId;

    private LocalDateTime createdDate;

    @Builder
    public CommonNftResponse(Long nftId, String name, String username, Long likeCount, Long favoriteCount, Long viewCount, String marketLink, String userImageUrl, String nftImageUrl, String nftVideoUrl,
                             String nftCollectionName, Long nftCollectionId, String assetContractAddress, String tokenId, String tagType, Long frameNftId, String userUrl, LocalDateTime localDate,
                             String description, MarketType marketType, Long marketId, String collectionName, Long assetId, LocalDateTime createdDate, Long backgroundSeq, Long orderSeq) {
        this.nftId = nftId;
        this.name = name;
        this.username = username;
        this.likeCount = likeCount;
        this.favoriteCount = favoriteCount;
        this.viewCount = viewCount;
        this.marketLink = marketLink;
        this.userImageUrl = userImageUrl;
        this.nftImageUrl = nftImageUrl;
        this.nftVideoUrl = nftVideoUrl;
        this.nftCollectionName = nftCollectionName;
        this.nftCollectionId = nftCollectionId;
        this.assetContractAddress = assetContractAddress;
        this.tokenId = tokenId;
        this.tagType = tagType;
        this.frameNftId = frameNftId;
        this.orderSeq = orderSeq;
        this.backgroundSeq = backgroundSeq;
        this.userUrl = userUrl;
        this.localDate = localDate;

        this.description = description;
        this.marketType = marketType;
        this.marketId = marketId;
        this.collectionName = collectionName;
        this.assetId = assetId;
        this.createdDate = createdDate;
    }


    @Override
    public int compareTo(CommonNftResponse o) {
        return 0;
    }


}
