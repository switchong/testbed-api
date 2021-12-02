package com.nftgram.core.repository.impl;

import com.nftgram.core.domain.nftgram.NftCollection;
import com.nftgram.core.repository.custom.NftCollectionCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NftCollectionRepositoryImpl implements NftCollectionCustomRepository {

    private final JPAQueryFactory queryFactory;

    public NftCollectionRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<NftCollection> findAll() {
        return null;
    }
}
