package com.nftgram.core.repository.impl;

import com.nftgram.core.repository.custom.NftFavoriteCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

@Repository
public class NftFavoriteRepositoryImpl implements NftFavoriteCustomRepository {
    private final JPAQueryFactory queryFactory;

    public NftFavoriteRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }
}
