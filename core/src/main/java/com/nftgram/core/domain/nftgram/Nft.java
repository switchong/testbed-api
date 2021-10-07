package com.nftgram.core.domain.nftgram;


import com.nftgram.core.domain.common.BaseEntity;
import com.nftgram.core.domain.common.value.ActiveStatus;
import com.nftgram.core.domain.nftgram.value.MaketType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Entity
@Builder
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Nft  extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nftId;  //PK

    @Column(nullable = false)
    private String assetContractAddress; //UK


    @Column(nullable = false , length = 300)
    private String tokenId; //UK


    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false , length = 10)
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
    @JoinColumn(name = "nft_asst_id" , nullable = false)
    private NftAsset nftAsset;


    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "nft_collection_id" , nullable = false)
    private NftCollection nftCollection;




    @OneToOne(mappedBy = "nft" , cascade = {CascadeType.PERSIST, CascadeType.DETACH}, orphanRemoval = true)
    private  List<NftProperty> nftProperties;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "active_status" , nullable = false , length = 10)
    private ActiveStatus activeStatus;

}
