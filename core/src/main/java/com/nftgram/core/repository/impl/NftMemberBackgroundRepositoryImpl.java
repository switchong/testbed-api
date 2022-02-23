package com.nftgram.core.repository.impl;

import com.nftgram.core.domain.nftgram.NftMemberBackground;
import com.nftgram.core.repository.custom.NftMemberBackgroundCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.nftgram.core.domain.nftgram.QNftMemberBackground.nftMemberBackground;

@Repository
@Slf4j
public class NftMemberBackgroundRepositoryImpl implements NftMemberBackgroundCustomRepository {

    private final JPAQueryFactory queryFactory;

    public NftMemberBackgroundRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<NftMemberBackground> memberBackgrounds(Pageable pageable, Long memberId) {
        List<NftMemberBackground> result = queryFactory
                .selectFrom(nftMemberBackground)
                .where(nftMemberBackground.nftMember.nftMemberId.eq(memberId))
                .orderBy(nftMemberBackground.bgOrder.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return result;
    }
}
