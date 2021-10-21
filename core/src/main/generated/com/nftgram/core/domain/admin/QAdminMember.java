package com.nftgram.core.domain.admin;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAdminMember is a Querydsl query type for AdminMember
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAdminMember extends EntityPathBase<AdminMember> {

    private static final long serialVersionUID = 1226703367L;

    public static final QAdminMember adminMember = new QAdminMember("adminMember");

    public final com.nftgram.core.domain.common.QBaseEntity _super = new com.nftgram.core.domain.common.QBaseEntity(this);

    public final StringPath adminId = createString("adminId");

    public final NumberPath<Long> ald = createNumber("ald", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastLoginDate = _super.lastLoginDate;

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDate = _super.updateDate;

    public QAdminMember(String variable) {
        super(AdminMember.class, forVariable(variable));
    }

    public QAdminMember(Path<? extends AdminMember> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAdminMember(PathMetadata metadata) {
        super(AdminMember.class, metadata);
    }

}

