package com.nftgram.core.domain.nftgram;


import com.nftgram.core.domain.common.BaseEntity;
import com.nftgram.core.domain.common.value.ActiveStatus;
import com.nftgram.core.domain.nftgram.value.MarketType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "nft")
@Setter
public class Nft  extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nftId;  //PK

    @Column(nullable = false , unique = true, length = 80)
    private String assetContractAddress; //UK

    @Column(nullable = false , unique = true, length = 300)
    private String tokenId; //UK

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private MarketType marketType;

    @Column(nullable = false)
    private Long marketId;

    @Column(name = "nft_member_id")
    private Long nft_member_id;


    @Column(length = 100)
    private String ownerUserName;

    @Column
    private String ownerProfileImageUrl;

    @Column(length = 80)
    private String ownerContractAddress;

    @Column(length = 100)
    private String creatorUserName;

    @Column
    private String creatorProfileImageUrl;

    @Column(length = 80)
    private String creatorContractAddress;

    @Column(nullable = false , length = 100)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private Long numSales;

    @Column
    private String imageUrl;

    @Column
    private String imagePreviewUrl;

    @Column
    private String imageThumbnailUrl;

    @Column
    private String imageOriginalUrl;

    @Column
    private String animationUrl;

    @Column
    private String animationOriginalUrl;

    @Column
    private String openseaLink;

    @Column
    private String externalLink;

    @Column
    private Long likeCount;

    @Column
    private Long favoriteCount;

    @Column
    private Long viewCount;

    @Column
    private String collectionName;

    @Column
    private LocalDate lastSaleDate;

    @Column(length = 80)
    private String lastSaleContractAddress;

    @Column(length = 100)
    private String lastSaleUserName;

    @Column
    private Long frameNftId;

    @Column
    private Long orderSeq;

    @Column
    private Long backgroundSeq;

    @Column
    private String lastSaleProfileImageUrl;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "nft_asset_id" , nullable = false)
    private NftAsset nftAsset;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "nft_collection_id" , nullable = false)
    private NftCollection nftCollection;

    @OneToMany(mappedBy = "nft" , cascade = {CascadeType.PERSIST, CascadeType.DETACH}, orphanRemoval = true)
    private List<NftProperty> nftProperties = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    @Column(name = "active_status" , nullable = false)
    private ActiveStatus activeStatus;

    @Transient
    private Integer propertyOrder;

    @Builder
    public Nft(String assetContractAddress, String tokenId,
               String ownerUserName, String nftId , String s, int i, int i1) {
        this.nft_member_id = nft_member_id;
        this.assetContractAddress = assetContractAddress;
        this.tokenId = tokenId;
        this.marketType = marketType;
        this.marketId = marketId;
        this.ownerUserName = ownerUserName;
        this.ownerProfileImageUrl = ownerProfileImageUrl;
        this.ownerContractAddress = ownerContractAddress;
        this.creatorUserName = creatorUserName;
        this.creatorProfileImageUrl = creatorProfileImageUrl;
        this.creatorContractAddress = creatorContractAddress;
        this.name = name;
        this.description = description;
        this.numSales = numSales;
        this.imageUrl = imageUrl;
        this.imagePreviewUrl = imagePreviewUrl;
        this.imageThumbnailUrl = imageThumbnailUrl;
        this.imageOriginalUrl = imageOriginalUrl;
        this.animationUrl = animationUrl;
        this.animationOriginalUrl = animationOriginalUrl;
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
        this.frameNftId = frameNftId;
        this.orderSeq = orderSeq;
        this.backgroundSeq = backgroundSeq;

        for (NftProperty nftProperty : nftProperties) {
            addNftProperty(nftProperty);
        }
    }

    public void addNftProperty(NftProperty nftProperty) {
        this.propertyOrder += 1;
        nftProperty.addNft(this);
        this.nftProperties.add(nftProperty);
    }

    public void deletePost() {
        this.activeStatus = ActiveStatus.DELETE;
    }
}
