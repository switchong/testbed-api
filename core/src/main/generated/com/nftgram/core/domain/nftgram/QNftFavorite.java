package com.nftgram.core.domain.nftgram;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNftFavorite is a Querydsl query type for NftFavorite
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QNftFavorite extends EntityPathBase<NftFavorite> {

    private static final long serialVersionUID = -1510024134L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNftFavorite nftFavorite = new QNftFavorite("nftFavorite");

    public final com.nftgram.core.domain.common.QBaseEntity _super = new com.nftgram.core.domain.common.QBaseEntity(this);

    public final EnumPath<com.nftgram.core.domain.common.value.ActiveStatus> activeStatus = createEnum("activeStatus", com.nftgram.core.domain.common.value.ActiveStatus.class);

    public final StringPath assetContractAddress = createString("assetContractAddress");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final NumberPath<Long> favId = createNumber("favId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastLoginDate = _super.lastLoginDate;

    public final QNft nft;

    public final QNftMember nftMember;

    public final StringPath tokenId = createString("tokenId");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDate = _super.updateDate;

    public QNftFavorite(String variable) {
        this(NftFavorite.class, forVariable(variable), INITS);
    }

    public QNftFavorite(Path<? extends NftFavorite> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QNftFavorite(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QNftFavorite(PathMetadata metadata, PathInits inits) {
        this(NftFavorite.class, metadata, inits);
    }

    public QNftFavorite(Class<? extends NftFavorite> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.nft = inits.isInitialized("nft") ? new QNft(forProperty("nft"), inits.get("nft")) : null;
        this.nftMember = inits.isInitialized("nftMember") ? new QNftMember(forProperty("nftMember"), inits.get("nftMember")) : null;
    }

}

