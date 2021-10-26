package com.nftgram.core.domain.nftgram;


import com.nftgram.core.domain.common.BaseEntity;
import com.nftgram.core.domain.common.value.ActiveStatus;
import com.nftgram.core.domain.nftgram.value.MaketType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table
public class Nft  extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nftId;  //PK

    @OneToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "nft_member_id", nullable = false)
    private NftMember nftMember;

    @Column(nullable = false)
    private String assetContractAddress; //UK

    @Column(nullable = false , length = 300)
    private String tokenId; //UK

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false , length = 10)
    private MaketType maketType;

    @Column(nullable = false)
    private Long marketId;

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

    @OneToMany(mappedBy = "nftId" , cascade = {CascadeType.PERSIST, CascadeType.DETACH}, orphanRemoval = true)
    private List<NftProperty> nftProperties = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    @Column(name = "active_status" , nullable = false , length = 10)
    private ActiveStatus activeStatus;

    @Transient
    private Integer propertyOrder;

    @Builder
    public Nft(NftMember nftMember, String assetContractAddress, String tokenId, MaketType maketType, Long marketId,
               String ownerUserName, String ownerProfileImageUrl, String ownerContractAddress, String createUserName,
               String createProfileImageUrl, String createContractAddress, String name, String description, Long numSales,
               String imageUrl, String imageOriginalUrl, String openseaLink, String externalLink, Long likeCount,
               Long favoriteCount, Long viewCount, String collectionName, LocalDate lastSaleDate, String lastSaleContractAddress,
               String lastSaleUserName, String lastSaleProfileImageUrl, NftAsset nftAsset, NftCollection nftCollection, List<NftProperty> nftProperties) {
        this.nftMember = nftMember;
        this.assetContractAddress = assetContractAddress;
        this.tokenId = tokenId;
        this.maketType = maketType;
        this.marketId = marketId;
        this.ownerUserName = ownerUserName;
        this.ownerProfileImageUrl = ownerProfileImageUrl;
        this.ownerContractAddress = ownerContractAddress;
        this.createUserName = createUserName;
        this.createProfileImageUrl = createProfileImageUrl;
        this.createContractAddress = createContractAddress;
        this.name = name;
        this.description = description;
        this.numSales = numSales;
        this.imageUrl = imageUrl;
        this.imageOriginalUrl = imageOriginalUrl;
        this.openseaLink = openseaLink;
        this.externalLink = externalLink;
        this.likeCount = likeCount;
        this.favoriteCount = favoriteCount;
        this.viewCount = viewCount;
        this.collectionName = collectionName;
        this.lastSaleDate = lastSaleDate;
        this.lastSaleContractAddress = lastSaleContractAddress;
        this.lastSaleUserName = lastSaleUserName;
        this.lastSaleProfileImageUrl = lastSaleProfileImageUrl;
        this.nftAsset = nftAsset;
        this.nftCollection = nftCollection;
        this.activeStatus = ActiveStatus.ACTIVE;

        for (NftProperty nftProperty : nftProperties) {
            addNftProperty(nftProperty);
        }
    }

    public void addNftProperty(NftProperty nftProperty) {
        this.propertyOrder += 1;
        nftProperty.addNft(this.nftId);
        this.nftProperties.add(nftProperty);
    }
}
