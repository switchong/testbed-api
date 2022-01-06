package com.nftgram.web.common.dto.response;

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

    private LocalDateTime localDate;


    @Builder
    public CommonNftResponse(Long nftId, String name, String username, Long likeCount, Long favoriteCount, Long viewCount, String marketLink, String userImageUrl, String nftImageUrl,
                             String nftCollectionName, Long nftCollectionId, String assetContractAddress, String tokenId, String tagType, LocalDateTime localDate) {
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
        this.localDate = localDate;
    }


    @Override
    public int compareTo(CommonNftResponse o) {
        return 0;
    }


}
