package com.nftgram.core.domain.nftgram;


import com.nftgram.core.domain.common.BaseEntity;
import com.nftgram.core.domain.common.value.ActiveStatus;
import com.nftgram.core.domain.nftgram.value.MaketType;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@Builder
@Table(name = "nft")
public class Nft  extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nftId;  //PK

    @Column(name = "asset_contract_address", nullable = false)
    private String assetContractAddress; //UK


    @Column(name = "token_id", nullable = false , length = 300)
    private String tokenId; //UK


    @Enumerated(value = EnumType.STRING)
    @Column(name = "maket_type" , nullable = false , length = 10)
    private MaketType maketType;

    @Column(nullable = false)
    private Long marketId;


    @OneToOne(fetch = FetchType.LAZY , optional = false)
    private NftMember nftMember;

    @Column(nullable = false)
    private String ownerUserName;

    @Column(nullable = false)
    private String ownerProfileImageUrl;

    @Column(nullable = false)
    private String ownerContractAddress;

    @Column(nullable = false)
    private String createUserName;

    @Column(nullable = false)
    private String createProfileImageUrl;

    @Column(nullable = false)
    private String createContractAddress;

    @Column(nullable = false , length = 100)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Long numSales;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private String imageOriginalUrl;

    @Column(nullable = false)
    private String openseaLink;

    @Column(nullable = false)
    private String externalLink;

    @Column(nullable = false)
    private Long likeCount;

    @Column(nullable = false)
    private Long favoriteCount;

    @Column(nullable = false)
    private Long viewCount;

    @Column(nullable = false)
    private String collectionName;

    @Column(nullable = false)
    private LocalDate lastSaleDate;

    @Column(nullable = false)
    private String lastSaleContractAddress;

    @Column(nullable = false)
    private String lastSaleUserName;

    @Column(nullable = false)
    private String lastSaleProfileImageUrl;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private NftAsset nftAsset;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private NftCollection nftCollection;


    @Enumerated(value = EnumType.STRING)
    @Column(name = "active_status" , nullable = false , length = 10)
    private ActiveStatus activeStatus;

}
