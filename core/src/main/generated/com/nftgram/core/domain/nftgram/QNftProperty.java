package com.nftgram.core.domain.nftgram;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNftProperty is a Querydsl query type for NftProperty
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QNftProperty extends EntityPathBase<NftProperty> {

    private static final long serialVersionUID = 741011571L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNftProperty nftProperty = new QNftProperty("nftProperty");

    public final QNft nft;

    public final NumberPath<Integer> order = createNumber("order", Integer.class);

    public final NumberPath<Long> propId = createNumber("propId", Long.class);

    public final NumberPath<Long> traitCount = createNumber("traitCount", Long.class);

    public final StringPath traitType = createString("traitType");

    public final StringPath traitValue = createString("traitValue");

    public QNftProperty(String variable) {
        this(NftProperty.class, forVariable(variable), INITS);
    }

    public QNftProperty(Path<? extends NftProperty> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QNftProperty(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QNftProperty(PathMetadata metadata, PathInits inits) {
        this(NftProperty.class, metadata, inits);
    }

    public QNftProperty(Class<? extends NftProperty> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.nft = inits.isInitialized("nft") ? new QNft(forProperty("nft"), inits.get("nft")) : null;
    }

}

