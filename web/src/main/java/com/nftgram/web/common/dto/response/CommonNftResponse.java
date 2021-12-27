package com.nftgram.web.common.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public class CommonNftResponse {

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

    private LocalDateTime localDate;


    @Builder
    public CommonNftResponse(Long nftId, String name, String username, Long likeCount, Long favoriteCount, Long viewCount, String marketLink, String userImageUrl, String nftImageUrl,
                             String nftCollectionName, Long nftCollectionId, String assetContractAddress, String tokenId, LocalDateTime localDate) {
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
        this.localDate = localDate;
    }


//    @Override
//    public int compareTo(CommonNftResponse o ){
//
//        if (this.nftId == o.nftId){
//            return 0;
//        }else  if (this.nftId  < o.nftId){
//            return  -1;
//        }else {
//            return 1;
//        }
//    }
}
