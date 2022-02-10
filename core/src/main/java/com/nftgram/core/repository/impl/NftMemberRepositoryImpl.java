package com.nftgram.core.repository.impl;

import com.nftgram.core.domain.common.value.ActiveStatus;
import com.nftgram.core.domain.nftgram.NftMember;
import com.nftgram.core.dto.request.NftMemberRequestDto;
import com.nftgram.core.repository.custom.NftMemberCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import static com.nftgram.core.domain.nftgram.QNftMember.nftMember;
import static com.nftgram.core.domain.nftgram.QNftMemberWallet.nftMemberWallet;

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
    public NftMember findNftUsername(String username) {
        NftMember result = queryFactory.selectFrom(nftMember)
                .where(nftMember.username.eq(username))
                .fetchOne();

        return result;
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
