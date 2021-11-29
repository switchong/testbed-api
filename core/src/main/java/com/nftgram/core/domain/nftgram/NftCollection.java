package com.nftgram.core.domain.nftgram;


import com.nftgram.core.domain.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name = "nft_collection")
public class NftCollection  extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long nftCollectionId;  //PK

    @Column(nullable = false , unique = true, length = 100)
    private String collectionSlug;

    @Column(nullable = false, length = 80)
    private String collectionName;

    @Column(columnDefinition = "TEXT")
    private String collectionDescription;

    @Column
    private String collectionBannerImageUrl;

    @Column
    private String collectionImageUrl;

    @Column
    private String collectionLargeImageUrl;

    @Column(length = 100)
    private String collectionRequireEmail;

    @Column(length = 100)
    private String collectionDiscordUrl;

    @Column(length = 100)
    private String collectionTelegramUrl;

    @Column(length = 100)
    private String collectionTwitterUsername;

    @Column(length = 100)
    private String collectionInstagramUsername;

    @Column(length = 100)
    private String collectionWikiUrl;

    @Builder
    public NftCollection(String collectionName,
                         String collectionDescription,
                         String collectionBannerImageUrl,
                         String collectionImageUrl,
                         String collectionLargeImageUrl,
                         String collectionRequireEmail,
                         String collectionDiscordUrl,
                         String collectionSlug,
                         String collectionTelegramUrl, String collectionTwitterUsername, String collectionInstagramUsername, String collectionWikiUrl) {
        this.collectionName = collectionName;
        this.collectionDescription = collectionDescription;
        this.collectionBannerImageUrl = collectionBannerImageUrl;
        this.collectionImageUrl = collectionImageUrl;
        this.collectionLargeImageUrl = collectionLargeImageUrl;
        this.collectionRequireEmail = collectionRequireEmail;
        this.collectionDiscordUrl = collectionDiscordUrl;
        this.collectionSlug = collectionSlug;
        this.collectionTelegramUrl = collectionTelegramUrl;
        this.collectionTwitterUsername = collectionTwitterUsername;
        this.collectionInstagramUsername = collectionInstagramUsername;
        this.collectionWikiUrl = collectionWikiUrl;
    }
}
