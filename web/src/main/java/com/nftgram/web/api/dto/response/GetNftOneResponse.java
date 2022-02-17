package com.nftgram.web.api.dto.response;

import com.nftgram.core.domain.nftgram.NftAsset;
import com.nftgram.core.domain.nftgram.NftCollection;
import com.nftgram.core.domain.nftgram.value.MarketType;
import com.nftgram.web.common.dto.response.NftPropertyResponse;
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

    private String description;

    private String assetContractAddress;

    private String tokenId;

    private MarketType marketType;

    private Long marketId;

    private Long likeCount;

    private Long favoriteCount;

    private Long viewCount;

    private String marketLink;

    private String username;

    private String userImageUrl;

    private String nftImageUrl;

    private String collectionName;

    private Long nftCollectionId;

    private Long nftAssetId;

    private Long frameNftId;

    private String userUrl;

    private String tagType;

    private LocalDateTime createdDate;

    private NftAsset asset;

    private NftCollection collections;

    private List<NftPropertyResponse> propList;

    @Builder
    public GetNftOneResponse(String likeFlag, Long nftId, String name, String description, String assetContractAddress, String tokenId, MarketType marketType,Long marketId,
                             Long likeCount, Long favoriteCount, Long viewCount, String marketLink, String username, String userImageUrl, String nftImageUrl,
                             String collectionName, Long nftCollectionId, Long nftAssetId, Long frameNftId, String userUrl, String tagType, LocalDateTime createdDate,
                             NftAsset asset, NftCollection collections, List<NftPropertyResponse> propList ) {
        this.likeFlag = likeFlag;
        this.nftId = nftId;
        this.name = name;
        this.description = description;
        this.assetContractAddress = assetContractAddress;
        this.tokenId = tokenId;
        this.marketType = marketType;
        this.marketId = marketId;
        this.likeCount = likeCount;
        this.favoriteCount = favoriteCount;
        this.viewCount = viewCount;
        this.marketLink = marketLink;
        this.username = username;
        this.userImageUrl = userImageUrl;
        this.nftImageUrl = nftImageUrl;
        this.collectionName = collectionName;
        this.nftCollectionId = nftCollectionId;
        this.nftAssetId = nftAssetId;
        this.frameNftId = frameNftId;
        this.userUrl = userUrl;
        this.tagType = tagType;
        this.createdDate = createdDate;
        this.asset = asset;
        this.collections = collections;
        this.propList = propList;
    }

}
