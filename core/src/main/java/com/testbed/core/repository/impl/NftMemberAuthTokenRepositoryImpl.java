package com.testbed.core.repository.impl;

import com.testbed.core.repository.custom.NftMemberAuthTokenCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class NftMemberAuthTokenRepositoryImpl implements NftMemberAuthTokenCustomRepository {
    private final JPAQueryFactory queryFactory;

    public NftMemberAuthTokenRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }
}
