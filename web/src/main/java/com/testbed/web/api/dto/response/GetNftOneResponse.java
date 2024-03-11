package com.testbed.web.api.dto.response;

import com.testbed.core.domain.testbed.NftAsset;
import com.testbed.core.domain.testbed.NftCollection;
import com.testbed.core.domain.testbed.value.MarketType;
import com.testbed.web.common.dto.response.NftPropertyResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetNftOneResponse {

    private String likeFlag;

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

    private NftAsset asset;

    private NftCollection collections;

    private List<NftPropertyResponse> propList;

    @Builder
    public GetNftOneResponse(String likeFlag, Long nftId, String name, String username, Long likeCount, Long favoriteCount, Long viewCount, String marketLink, String userImageUrl, String nftImageUrl, String nftVideoUrl,
                             String nftCollectionName, Long nftCollectionId, String assetContractAddress, String tokenId, String tagType, Long frameNftId, String userUrl, LocalDateTime localDate,
                             String description, MarketType marketType, Long marketId, String collectionName, Long assetId, LocalDateTime createdDate, Long backgroundSeq, Long orderSeq,
                             NftAsset asset, NftCollection collections, List<NftPropertyResponse> propList ) {
        this.likeFlag = likeFlag;
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
        this.asset = asset;
        this.collections = collections;
        this.propList = propList;
    }

}
