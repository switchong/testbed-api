package com.nftgram.core.repository.impl;

import com.nftgram.core.domain.nftgram.NftMember;
import com.nftgram.core.repository.custom.NftMemberCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import static com.nftgram.core.domain.nftgram.QNftMember.nftMember;

@Repository
public class NftMemberRepositoryImpl implements NftMemberCustomRepository {

    private final JPAQueryFactory queryFactory;

    public NftMemberRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public NftMember findByNftMemberUserId(String nftMemberUserId) {
        NftMember result = queryFactory.selectFrom(nftMember)
                .where(nftMember.nftMemberUserId.eq(nftMemberUserId))
                .fetchOne();
        return result;
    }

    @Override
    public NftMember findByNftMemberId(Long nftMemberId) {
        NftMember nftMemberResult = queryFactory.selectFrom(nftMember)
                .where(nftMember.nftMemberId.eq(nftMemberId))
                .fetchOne();
        return nftMemberResult;
    }

}
