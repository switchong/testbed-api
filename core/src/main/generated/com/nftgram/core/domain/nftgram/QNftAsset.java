package com.nftgram.core.domain.nftgram;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QNftAsset is a Querydsl query type for NftAsset
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QNftAsset extends EntityPathBase<NftAsset> {

    private static final long serialVersionUID = 1683229970L;

    public static final QNftAsset nftAsset = new QNftAsset("nftAsset");

    public final com.nftgram.core.domain.common.QBaseEntity _super = new com.nftgram.core.domain.common.QBaseEntity(this);

    public final StringPath assetContractAddress = createString("assetContractAddress");

    public final StringPath assetContractDescription = createString("assetContractDescription");

    public final StringPath assetContractImageUrl = createString("assetContractImageUrl");

    public final StringPath assetContractName = createString("assetContractName");

    public final StringPath assetContractOwner = createString("assetContractOwner");

    public final StringPath assetContractPayoutAddress = createString("assetContractPayoutAddress");

    public final StringPath assetContractSymbol = createString("assetContractSymbol");

    public final EnumPath<com.nftgram.core.domain.nftgram.value.ContractSchema> contractSchema = createEnum("contractSchema", com.nftgram.core.domain.nftgram.value.ContractSchema.class);

    public final EnumPath<com.nftgram.core.domain.nftgram.value.ContractType> contractType = createEnum("contractType", com.nftgram.core.domain.nftgram.value.ContractType.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastLoginDate = _super.lastLoginDate;

    public final NumberPath<Long> nftAssetId = createNumber("nftAssetId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDate = _super.updateDate;

    public QNftAsset(String variable) {
        super(NftAsset.class, forVariable(variable));
    }

    public QNftAsset(Path<? extends NftAsset> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNftAsset(PathMetadata metadata) {
        super(NftAsset.class, metadata);
    }

}

