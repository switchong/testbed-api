package com.nftgram.core.domain.nftgram;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNft is a Querydsl query type for Nft
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QNft extends EntityPathBase<Nft> {

    private static final long serialVersionUID = -727708546L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNft nft = new QNft("nft");

    public final com.nftgram.core.domain.common.QBaseEntity _super = new com.nftgram.core.domain.common.QBaseEntity(this);

    public final EnumPath<com.nftgram.core.domain.common.value.ActiveStatus> activeStatus = createEnum("activeStatus", com.nftgram.core.domain.common.value.ActiveStatus.class);

    public final StringPath assetContractAddress = createString("assetContractAddress");

    public final StringPath collectionName = createString("collectionName");

    public final StringPath createContractAddress = createString("createContractAddress");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final StringPath createProfileImageUrl = createString("createProfileImageUrl");

    public final StringPath createUserName = createString("createUserName");

    public final StringPath description = createString("description");

    public final StringPath externalLink = createString("externalLink");

    public final NumberPath<Long> favoriteCount = createNumber("favoriteCount", Long.class);

    public final StringPath imageOriginalUrl = createString("imageOriginalUrl");

    public final StringPath imageUrl = createString("imageUrl");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastLoginDate = _super.lastLoginDate;

    public final StringPath lastSaleContractAddress = createString("lastSaleContractAddress");

    public final DatePath<java.time.LocalDate> lastSaleDate = createDate("lastSaleDate", java.time.LocalDate.class);

    public final StringPath lastSaleProfileImageUrl = createString("lastSaleProfileImageUrl");

    public final StringPath lastSaleUserName = createString("lastSaleUserName");

    public final NumberPath<Long> likeCount = createNumber("likeCount", Long.class);

    public final EnumPath<com.nftgram.core.domain.nftgram.value.MaketType> maketType = createEnum("maketType", com.nftgram.core.domain.nftgram.value.MaketType.class);

    public final NumberPath<Long> marketId = createNumber("marketId", Long.class);

    public final StringPath name = createString("name");

    public final QNftAsset nftAsset;

    public final QNftCollection nftCollection;

    public final NumberPath<Long> nftId = createNumber("nftId", Long.class);

    public final QNftMember nftMember;

    public final ListPath<NftProperty, QNftProperty> nftProperties = this.<NftProperty, QNftProperty>createList("nftProperties", NftProperty.class, QNftProperty.class, PathInits.DIRECT2);

    public final NumberPath<Long> numSales = createNumber("numSales", Long.class);

    public final StringPath openseaLink = createString("openseaLink");

    public final StringPath ownerContractAddress = createString("ownerContractAddress");

    public final StringPath ownerProfileImageUrl = createString("ownerProfileImageUrl");

    public final StringPath ownerUserName = createString("ownerUserName");

    public final StringPath tokenId = createString("tokenId");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDate = _super.updateDate;

    public final NumberPath<Long> viewCount = createNumber("viewCount", Long.class);

    public QNft(String variable) {
        this(Nft.class, forVariable(variable), INITS);
    }

    public QNft(Path<? extends Nft> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QNft(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QNft(PathMetadata metadata, PathInits inits) {
        this(Nft.class, metadata, inits);
    }

    public QNft(Class<? extends Nft> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.nftAsset = inits.isInitialized("nftAsset") ? new QNftAsset(forProperty("nftAsset")) : null;
        this.nftCollection = inits.isInitialized("nftCollection") ? new QNftCollection(forProperty("nftCollection")) : null;
        this.nftMember = inits.isInitialized("nftMember") ? new QNftMember(forProperty("nftMember"), inits.get("nftMember")) : null;
    }

}

