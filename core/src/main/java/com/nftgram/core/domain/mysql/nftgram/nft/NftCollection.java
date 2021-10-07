package com.nftgram.core.domain.mysql.nftgram.nft;


import com.nftgram.core.domain.BaseEntity;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Builder
public class NftCollection  extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long nftCollectionId;  //Pk

    private String collectionName;

    private String collectionDescription;

    private String collectionBannerImageUrl;

    private String collectionImageUrl;

    private String collectionLargeImageUrl;

    private String collectionRequireEmail;

    private String collectionDiscordUrl;

    private String collectionSlug;

    private String collectionTelegramUrl;

    private String collectionTwitterUsername;

    private String collectionInstagramUsername;

    private String collectionWikiUrl;




}
