package com.nftgram.core.repository.impl;

import com.nftgram.core.domain.common.value.ActiveStatus;
import com.nftgram.core.domain.nftgram.NftLike;
import com.nftgram.core.repository.custom.NftLikeCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.nftgram.core.domain.common.value.ActiveStatus.*;
import static com.nftgram.core.domain.nftgram.QNftLike.nftLike;

@Repository
public class NftLikeRepositoryImpl implements NftLikeCustomRepository {

    private final JPAQueryFactory queryFactory;

    public NftLikeRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<NftLike> findByLikeNftMemberId(Long nftId, Long nftMemberId) {
        List<NftLike> nftLikeList = queryFactory.selectFrom(nftLike)
                .where(nftLike.nft.nftId.eq(nftId),
                        nftLike.nftMember.nftMemberId.eq(nftMemberId),
                        nftLike.activeStatus.eq(ACTIVE))
                .fetch();

        return nftLikeList;
    }

    @Override
    public List<NftLike> findByLikeNftId(Long nftId) {
        List<NftLike> nftLikeList = queryFactory.selectFrom(nftLike)
                .where(nftLike.nft.nftId.eq(nftId),
                        nftLike.activeStatus.eq(ACTIVE))
                .fetch();

        return nftLikeList;
    }

    @Override
    public NftLike findByLikeMemberId(Long nftMemberId) {
        NftLike nftLikeList = queryFactory.selectFrom(nftLike)
                .where(nftLike.nftMember.nftMemberId.eq(nftMemberId),
                        nftLike.activeStatus.eq(ACTIVE))
                .fetchOne();

        return nftLikeList;
    }

    @Override
    public NftLike findByLikeMemberIdOne(Long nftId, Long nftMemberId) {
        NftLike nftLikeList = queryFactory.selectFrom(nftLike)
                .where(nftLike.nft.nftId.eq(nftId),
                        nftLike.nftMember.nftMemberId.eq(nftMemberId))
                .fetchOne();

        return nftLikeList;
    }

    @Override
    public void deleteByLikeMember(Long likeId, ActiveStatus activeStatus) {
        long execute = queryFactory.update(nftLike)
                .set(nftLike.activeStatus, activeStatus)
                .where(nftLike.likeId.eq(likeId))
                .execute();

    }

}
