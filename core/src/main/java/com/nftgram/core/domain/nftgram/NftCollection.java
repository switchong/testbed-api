package com.nftgram.core.domain.nftgram;


import com.nftgram.core.domain.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Getter

@Table
public class NftCollection  extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long nftCollectionId;  //PK

    @Column(nullable = false , length = 80)
    private String collectionName;

    @Column(nullable = false)
    private String collectionDescription;

    @Column(nullable = false)
    private String collectionBannerImageUrl;

    @Column(nullable = false)
    private String collectionImageUrl;

    @Column(nullable = false)
    private String collectionLargeImageUrl;

    @Column(nullable = false , length = 100)
    private String collectionRequireEmail;

    @Column(nullable = false , length = 100)
    private String collectionDiscordUrl;

    @Column(nullable = false , length = 50)
    private String collectionSlug;

    @Column(nullable = false , length = 100)
    private String collectionTelegramUrl;

    @Column(nullable = false ,length = 100)
    private String collectionTwitterUsername;

    @Column(nullable = false ,length = 100)
    private String collectionInstagramUsername;

    @Column(nullable = false ,length = 100)
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
