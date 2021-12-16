package com.nftgram.core.repository.impl;

import com.nftgram.core.domain.nftgram.NftProperty;
import com.nftgram.core.dto.NftPropGroupDto;
import com.nftgram.core.repository.custom.NftPropertyCustomRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.nftgram.core.domain.nftgram.QNftProperty.nftProperty;

@Repository
public class NftPropertyRepositoryImpl implements NftPropertyCustomRepository {
    private final JPAQueryFactory queryFactory;

    public NftPropertyRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<NftProperty> findByPropertiesNftId(Long nftId) {
        List<NftProperty> result = queryFactory.selectFrom(nftProperty)
                .where(nftProperty.nft.nftId.eq(nftId))
                .fetch();
        return result;
    }

    @Override
    public List<NftPropGroupDto> findByTraitTypeGroupCount() {
        List<NftPropGroupDto> result = queryFactory.select(Projections.constructor(NftPropGroupDto.class,
                        nftProperty.traitType.upper().as("traitType"),
                        nftProperty.traitValue,
                        nftProperty.propId.count().as("traitCount")))
                .from(nftProperty)
                .groupBy(nftProperty.traitType.upper())
                .fetch();

        return result;
    }

    @Override
    public List<NftPropGroupDto> findByTraitTypeValueGroupCount() {
        List<NftPropGroupDto> result = queryFactory.select(Projections.constructor(NftPropGroupDto.class,
                        nftProperty.traitType.upper().as("traitType"),
                        nftProperty.traitValue,
                        nftProperty.propId.count().as("traitCount")))
                .from(nftProperty)
                .groupBy(nftProperty.traitType.upper(), nftProperty.traitValue)
                .fetch();

        return result;
    }
}