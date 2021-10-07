package com.nftgram.core.domain.mysql.nftgram;


import com.nftgram.core.domain.BaseEntity;
import com.nftgram.core.domain.mysql.common.value.ActiveStatus;
import com.nftgram.core.domain.mysql.nftgram.value.MaketType;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@Builder
public class Nft  extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nftId;  //PK

    @Column(name = "asset_Contract_Address", nullable = false)
    private String assetContractAddress; //UK


    @Column(name = "tokenId", nullable = false , length = 300)
    private String tokenId; //UK


    @Enumerated(value = EnumType.STRING)
    @Column(name = "maket_type" , nullable = false , length = 10)
    private MaketType maketType;

    @Column(nullable = false)
    private Long marketId;


    @OneToOne(fetch = FetchType.LAZY , optional = false)
    private NftMember nftMember;

    private String ownerUserName;

    private String ownerProfileImageUrl;

    private String ownerContractAddress;

    private String createUserName;

    private String createProfileImageUrl;

    private String createContractAddress;

    private String name;

    private String description;

    private Long numSales;

    private String imageUrl;

    private String imageOriginalUrl;

    private String openseaLink;

    private String externalLink;

    private Long likeCount;

    private Long favoriteCount;

    private Long viewCount;

    private String collectionName;

    private LocalDate lastSaleDate;

    private String lastSaleContractAddress;

    private String lastSaleUserName;

    private String lastSaleProfileImageUrl;


    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private NftAsset nftAsset;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private NftCollection nftCollection;


    @Enumerated(value = EnumType.STRING)
    @Column(name = "active_status" , nullable = false , length = 10)
    private ActiveStatus activeStatus;


}
