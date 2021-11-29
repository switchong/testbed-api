package com.nftgram.web.main.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MainResponse {
    private Boolean findFlag;

    private String username;

    private Long likeCount;

    private Long favoriteCount;

    private String userImageUrl;

    private String nftImageUrl;

    private Long nftCollectionId;

    private LocalDateTime localDate;

    @Builder
    public MainResponse(String username, Long likeCount, Long favoriteCount, String userImageUrl, String nftImageUrl,Long nftCollectionId , LocalDateTime localDate) {
        this.username = username;
        this.likeCount = likeCount;
        this.favoriteCount = favoriteCount;
        this.userImageUrl = userImageUrl;
        this.nftImageUrl = nftImageUrl;
        this.nftCollectionId = nftCollectionId;
        this.localDate = localDate;
    }

}
