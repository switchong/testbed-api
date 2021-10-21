package com.nftgram.core.domain.nftgram;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNftMember is a Querydsl query type for NftMember
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QNftMember extends EntityPathBase<NftMember> {

    private static final long serialVersionUID = 970960056L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNftMember nftMember = new QNftMember("nftMember");

    public final com.nftgram.core.domain.common.QBaseEntity _super = new com.nftgram.core.domain.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final StringPath discode = createString("discode");

    public final StringPath displayStype = createString("displayStype");

    public final StringPath facebook = createString("facebook");

    public final com.nftgram.core.domain.common.QImageStorage imageStorage;

    public final StringPath instagram = createString("instagram");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastLoginDate = _super.lastLoginDate;

    public final NumberPath<Long> nftMemberId = createNumber("nftMemberId", Long.class);

    public final StringPath twitter = createString("twitter");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDate = _super.updateDate;

    public final StringPath username = createString("username");

    public final StringPath walletContractAddress = createString("walletContractAddress");

    public final EnumPath<com.nftgram.core.domain.nftgram.value.WalletType> walletType = createEnum("walletType", com.nftgram.core.domain.nftgram.value.WalletType.class);

    public QNftMember(String variable) {
        this(NftMember.class, forVariable(variable), INITS);
    }

    public QNftMember(Path<? extends NftMember> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QNftMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QNftMember(PathMetadata metadata, PathInits inits) {
        this(NftMember.class, metadata, inits);
    }

    public QNftMember(Class<? extends NftMember> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.imageStorage = inits.isInitialized("imageStorage") ? new com.nftgram.core.domain.common.QImageStorage(forProperty("imageStorage")) : null;
    }

}

