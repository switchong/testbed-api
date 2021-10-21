package com.nftgram.core.domain.nftgram;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNftComment is a Querydsl query type for NftComment
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QNftComment extends EntityPathBase<NftComment> {

    private static final long serialVersionUID = 36507649L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNftComment nftComment = new QNftComment("nftComment");

    public final EnumPath<com.nftgram.core.domain.common.value.ActiveStatus> activeStatus = createEnum("activeStatus", com.nftgram.core.domain.common.value.ActiveStatus.class);

    public final StringPath assetContractAddress = createString("assetContractAddress");

    public final NumberPath<Long> commId = createNumber("commId", Long.class);

    public final NumberPath<Long> depth = createNumber("depth", Long.class);

    public final QNft nft;

    public final QNftMember nftMember;

    public final NumberPath<Long> parent = createNumber("parent", Long.class);

    public final StringPath tokenId = createString("tokenId");

    public QNftComment(String variable) {
        this(NftComment.class, forVariable(variable), INITS);
    }

    public QNftComment(Path<? extends NftComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QNftComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QNftComment(PathMetadata metadata, PathInits inits) {
        this(NftComment.class, metadata, inits);
    }

    public QNftComment(Class<? extends NftComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.nft = inits.isInitialized("nft") ? new QNft(forProperty("nft"), inits.get("nft")) : null;
        this.nftMember = inits.isInitialized("nftMember") ? new QNftMember(forProperty("nftMember"), inits.get("nftMember")) : null;
    }

}

