package com.nftgram.core.domain.nftgram;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNftLike is a Querydsl query type for NftLike
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QNftLike extends EntityPathBase<NftLike> {

    private static final long serialVersionUID = 885899573L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNftLike nftLike = new QNftLike("nftLike");

    public final EnumPath<com.nftgram.core.domain.common.value.ActiveStatus> activeStatus = createEnum("activeStatus", com.nftgram.core.domain.common.value.ActiveStatus.class);

    public final StringPath assetContractAddress = createString("assetContractAddress");

    public final NumberPath<Long> likeId = createNumber("likeId", Long.class);

    public final QNft nft;

    public final QNftMember nftMember;

    public final StringPath tokenId = createString("tokenId");

    public QNftLike(String variable) {
        this(NftLike.class, forVariable(variable), INITS);
    }

    public QNftLike(Path<? extends NftLike> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QNftLike(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QNftLike(PathMetadata metadata, PathInits inits) {
        this(NftLike.class, metadata, inits);
    }

    public QNftLike(Class<? extends NftLike> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.nft = inits.isInitialized("nft") ? new QNft(forProperty("nft"), inits.get("nft")) : null;
        this.nftMember = inits.isInitialized("nftMember") ? new QNftMember(forProperty("nftMember"), inits.get("nftMember")) : null;
    }

}

