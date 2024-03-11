package com.testbed.core.repository.impl;

import com.testbed.core.repository.custom.NftFavoriteCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

@Repository
public class NftFavoriteRepositoryImpl implements NftFavoriteCustomRepository {
    private final JPAQueryFactory queryFactory;

    public NftFavoriteRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }
}
