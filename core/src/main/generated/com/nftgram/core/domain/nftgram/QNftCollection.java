package com.nftgram.core.domain.nftgram;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QNftCollection is a Querydsl query type for NftCollection
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QNftCollection extends EntityPathBase<NftCollection> {

    private static final long serialVersionUID = -1667722820L;

    public static final QNftCollection nftCollection = new QNftCollection("nftCollection");

    public final com.nftgram.core.domain.common.QBaseEntity _super = new com.nftgram.core.domain.common.QBaseEntity(this);

    public final StringPath collectionBannerImageUrl = createString("collectionBannerImageUrl");

    public final StringPath collectionDescription = createString("collectionDescription");

    public final StringPath collectionDiscordUrl = createString("collectionDiscordUrl");

    public final StringPath collectionImageUrl = createString("collectionImageUrl");

    public final StringPath collectionInstagramUsername = createString("collectionInstagramUsername");

    public final StringPath collectionLargeImageUrl = createString("collectionLargeImageUrl");

    public final StringPath collectionName = createString("collectionName");

    public final StringPath collectionRequireEmail = createString("collectionRequireEmail");

    public final StringPath collectionSlug = createString("collectionSlug");

    public final StringPath collectionTelegramUrl = createString("collectionTelegramUrl");

    public final StringPath collectionTwitterUsername = createString("collectionTwitterUsername");

    public final StringPath collectionWikiUrl = createString("collectionWikiUrl");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastLoginDate = _super.lastLoginDate;

    public final NumberPath<Long> nftCollectionId = createNumber("nftCollectionId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDate = _super.updateDate;

    public QNftCollection(String variable) {
        super(NftCollection.class, forVariable(variable));
    }

    public QNftCollection(Path<? extends NftCollection> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNftCollection(PathMetadata metadata) {
        super(NftCollection.class, metadata);
    }

}

