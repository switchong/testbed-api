package com.testbed.core.repository.impl;

import com.testbed.core.domain.common.value.ActiveStatus;
import com.testbed.core.domain.nftgram.NftComment;
import com.testbed.core.dto.NftCommentMemberDto;
import com.testbed.core.repository.custom.NftCommentCustomRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.testbed.core.domain.nftgram.QNftComment.nftComment;
import static com.testbed.core.domain.nftgram.QNftMember.nftMember;

@Repository
public class NftCommentRepositoryImpl implements NftCommentCustomRepository {
    private final JPAQueryFactory queryFactory;

    public NftCommentRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<NftCommentMemberDto> getCommentList(Long nftId,Long page,Long size) {
        List<NftCommentMemberDto> result = queryFactory.select(Projections.constructor(NftCommentMemberDto.class, nftComment, nftMember))
                .from(nftComment)
                .join(nftComment.nftMember, nftMember)
                .where(nftComment.nft.nftId.eq(nftId))
                .where(nftComment.activeStatus.eq(ActiveStatus.ACTIVE))
                .orderBy(nftComment.commId.desc())
                .fetch();

        return result;
    }

    @Override
    public Long updateCommentStatus(NftComment commentTable) {
        return null;
    }

    @Override
    public Long deleteCommentStatus(Long commId) {
        Long result = queryFactory.update(nftComment)
                .set(nftComment.activeStatus, ActiveStatus.DELETE)
                .where(nftComment.commId.eq(commId))
                .execute();

        return result;
    }
}
