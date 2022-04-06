package com.nftgram.core.repository.impl;

import com.nftgram.core.domain.common.value.ActiveStatus;
import com.nftgram.core.domain.member.MemberStatus;
import com.nftgram.core.domain.nftgram.NftMember;
import com.nftgram.core.dto.request.NftMemberRequestDto;
import com.nftgram.core.repository.custom.NftMemberCustomRepository;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import static com.nftgram.core.domain.nftgram.QNft.nft;
import static com.nftgram.core.domain.nftgram.QNftAsset.nftAsset;
import static com.nftgram.core.domain.nftgram.QNftMember.nftMember;
import static com.nftgram.core.domain.nftgram.QNftMemberWallet.nftMemberWallet;
import static org.springframework.data.repository.support.PageableExecutionUtils.getPage;

@Repository
@Slf4j
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


    @Override
    public Long updateNftMember(NftMemberRequestDto updateDto, Long memberId) {
        Long result = queryFactory.update(nftMember)
                .set(nftMember.username , updateDto.getUsername())
                .set(   nftMember.instagram , updateDto.getInstagram())
                .set(   nftMember.facebook ,  updateDto.getFacebook() )
                .set(   nftMember.discord ,  updateDto.getDiscord() )
                .set(   nftMember.twitter ,  updateDto.getTwitter())
                .where(nftMember.nftMemberId.eq(memberId))
                .execute();

        return result;
    }

    @Override
    public Page<NftMember> findByNftMemberList(Pageable pageable, String keyword) {
        JPQLQuery<NftMember> resultMemberList = queryFactory.selectFrom(nftMember)
                .where(
                        likeUserId(keyword),
                        nftMember.memberStatus.eq(MemberStatus.ACTIVE)
                )
                .orderBy(nftMember.createDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        QueryResults<NftMember>  queryResults  = resultMemberList.fetchResults();

        return  getPage(queryResults.getResults() , pageable ,  () -> queryResults.getTotal());

    }
    private BooleanExpression likeUserId(String nftMemberId) {

        if (StringUtils.isEmpty(nftMemberId)){
            return  null;
        }


        return nftMember.nftMemberId.like(nftMemberId + "%");
    }
    @Override
    public NftMember findNftUsername(String username) {
        NftMember result = queryFactory.selectFrom(nftMember)
                .where(nftMember.username.eq(username))
                .fetchOne();

        return result;

    }

    @Override
    public boolean findNftMemberBackgroundFlag(Long memberId) {
        // order_seq 컬럼 미설정시 맨뒤로
        NumberExpression<Integer> caseBuilder = new CaseBuilder().when(nft.orderSeq.ne(Long.valueOf(0))).then(1).otherwise(2);

        Long nftResult = queryFactory.select(nft.nftId)
                .from(nft)
                .join(nft.nftAsset, nftAsset)
                .where(nft.imageUrl.isNotEmpty(),
                        nft.activeStatus.eq(ActiveStatus.ACTIVE),
                        nft.nft_member_id.eq(memberId),
                        nft.orderSeq.ne(Long.valueOf(0)))
                .orderBy(caseBuilder.asc(), nft.orderSeq.asc())
                .fetchCount();
        boolean editFlag = false;

        if(nftResult > 0) {
            editFlag = true;
        }

        return editFlag;
    }

    @Override
    public NftMember findNftMemberWalletAddress(String address) {
        NftMember result = queryFactory.selectFrom(nftMember)
                .from(nftMemberWallet)
                .join(nftMemberWallet.nftMember, nftMember)
                .where(nftMemberWallet.walletContractAddress.eq(address),
                        nftMemberWallet.activeStatus.eq(ActiveStatus.ACTIVE))
                .fetchOne();

        return result;
    }




}
