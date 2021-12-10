package com.nftgram.core.repository.impl;

import com.nftgram.core.repository.custom.NftPropertyCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

@Repository
public class NftPropertyRepositoryImpl implements NftPropertyCustomRepository {
    private final JPAQueryFactory queryFactory;

    public NftPropertyRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }
}
