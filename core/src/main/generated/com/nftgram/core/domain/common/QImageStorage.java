package com.nftgram.core.domain.common;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QImageStorage is a Querydsl query type for ImageStorage
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QImageStorage extends EntityPathBase<ImageStorage> {

    private static final long serialVersionUID = -1127301088L;

    public static final QImageStorage imageStorage = new QImageStorage("imageStorage");

    public final EnumPath<com.nftgram.core.domain.common.value.ActiveStatus> activeStatus = createEnum("activeStatus", com.nftgram.core.domain.common.value.ActiveStatus.class);

    public final StringPath fileName = createString("fileName");

    public final StringPath filePath = createString("filePath");

    public final StringPath fileSize = createString("fileSize");

    public final EnumPath<com.nftgram.core.domain.common.value.FileType> fileType = createEnum("fileType", com.nftgram.core.domain.common.value.FileType.class);

    public final NumberPath<Long> imageId = createNumber("imageId", Long.class);

    public final EnumPath<com.nftgram.core.domain.common.value.ImageType> imageType = createEnum("imageType", com.nftgram.core.domain.common.value.ImageType.class);

    public final NumberPath<Long> parentId = createNumber("parentId", Long.class);

    public final StringPath realPath = createString("realPath");

    public final EnumPath<com.nftgram.core.domain.common.value.TableType> tableType = createEnum("tableType", com.nftgram.core.domain.common.value.TableType.class);

    public QImageStorage(String variable) {
        super(ImageStorage.class, forVariable(variable));
    }

    public QImageStorage(Path<? extends ImageStorage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QImageStorage(PathMetadata metadata) {
        super(ImageStorage.class, metadata);
    }

}

