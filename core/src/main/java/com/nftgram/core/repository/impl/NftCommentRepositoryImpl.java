package com.nftgram.core.repository.impl;

import com.nftgram.core.repository.custom.NftCommentCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

@Repository
public class NftCommentRepositoryImpl implements NftCommentCustomRepository {
    private final JPAQueryFactory queryFactory;

    public NftCommentRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }
}
