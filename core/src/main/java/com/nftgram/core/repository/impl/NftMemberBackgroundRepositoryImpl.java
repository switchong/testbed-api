package com.nftgram.core.repository.impl;

import com.nftgram.core.domain.nftgram.NftMemberBackground;
import com.nftgram.core.repository.custom.NftMemberBackgroundCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.nftgram.core.domain.nftgram.QNft.nft;
import static com.nftgram.core.domain.nftgram.QNftMemberBackground.nftMemberBackground;

public class NftMemberBackgroundRepositoryImpl implements NftMemberBackgroundCustomRepository {

    private final JPAQueryFactory queryFactory;


    public NftMemberBackgroundRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<NftMemberBackground> memberBackgrounds(Pageable pageable, Long memberId) {
        List<NftMemberBackground> result = queryFactory
                .select(nftMemberBackground)
                .from(nftMemberBackground)
                .leftJoin(nftMemberBackground.nft, nft)
                .where(nftMemberBackground.nftMember.nftMemberId.eq(memberId))
                .orderBy(nftMemberBackground.bgOrder.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return result;
    }
}
