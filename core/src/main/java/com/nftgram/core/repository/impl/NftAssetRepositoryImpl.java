package com.nftgram.core.repository.impl;

import com.nftgram.core.repository.custom.NftAssetCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

@Repository
public class NftAssetRepositoryImpl implements NftAssetCustomRepository {
    private final JPAQueryFactory queryFactory;

    public NftAssetRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }
}
