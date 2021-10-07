package com.nftgram.core.domain.nftgram;


import com.nftgram.core.domain.common.BaseEntity;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Builder
@Table
public class NftCollection  extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long nftCollectionId;  //PK

    @Column(nullable = false)
    private String collectionName;

    @Column(nullable = false)
    private String collectionDescription;

    @Column(nullable = false)
    private String collectionBannerImageUrl;

    @Column(nullable = false)
    private String collectionImageUrl;

    @Column(nullable = false)
    private String collectionLargeImageUrl;

    @Column(nullable = false)
    private String collectionRequireEmail;

    @Column(nullable = false)
    private String collectionDiscordUrl;

    @Column(nullable = false)
    private String collectionSlug;

    @Column(nullable = false)
    private String collectionTelegramUrl;

    @Column(nullable = false)
    private String collectionTwitterUsername;

    @Column(nullable = false)
    private String collectionInstagramUsername;

    @Column(nullable = false)
    private String collectionWikiUrl;

}
