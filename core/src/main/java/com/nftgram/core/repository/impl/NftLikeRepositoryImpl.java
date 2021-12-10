package com.nftgram.core.repository.impl;

import com.nftgram.core.domain.nftgram.NftLike;
import com.nftgram.core.repository.custom.NftLikeCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.nftgram.core.domain.nftgram.QNftLike.nftLike;

@Repository
public class NftLikeRepositoryImpl implements NftLikeCustomRepository {

    private final JPAQueryFactory queryFactory;

    public NftLikeRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public List<NftLike> findNftLikeMemberId(Long nftId, Long nftMemberId) {
        List<NftLike> nftLikeList = queryFactory.selectFrom(nftLike)
                .where(nftLike.nft.nftId.eq(nftId))
                .where(nftLike.nftMember.nftMemberId.eq(nftMemberId))
                .fetch();

        return nftLikeList;
    }

}
