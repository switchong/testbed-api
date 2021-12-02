package com.nftgram.web.gallery.dto;

import com.nftgram.core.repository.NftCollectionRepository;
import com.nftgram.core.repository.NftRepository;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GalleryDto {

    private NftRepository nftRepository;

    private NftCollectionRepository nftCollectionRepository;

    private Long nftId;

    private String name;

    private String username;

    private Long likeCount;

    private Long favoriteCount;

    private String userImageUrl;

    private String nftImageUrl;

    private Long nftCollectionId;

    private LocalDateTime localDate;

    @Builder
    public GalleryDto(Long nftId, String name, String username, Long likeCount, Long favoriteCount, String userImageUrl, String nftImageUrl,Long nftCollectionId , LocalDateTime localDate) {
        this.nftId = nftId;
        this.name = name;
        this.username = username;
        this.likeCount = likeCount;
        this.favoriteCount = favoriteCount;
        this.userImageUrl = userImageUrl;
        this.nftImageUrl = nftImageUrl;
        this.nftCollectionId = nftCollectionId;
        this.localDate = localDate;
    }
}
