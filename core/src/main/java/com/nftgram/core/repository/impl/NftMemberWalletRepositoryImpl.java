package com.nftgram.core.repository.impl;

import com.nftgram.core.domain.common.value.ActiveStatus;
import com.nftgram.core.domain.nftgram.NftMemberWallet;
import com.nftgram.core.repository.custom.NftMemberWalletCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.nftgram.core.domain.nftgram.QNftMemberWallet.nftMemberWallet;

@Repository
@RequiredArgsConstructor
public class NftMemberWalletRepositoryImpl implements NftMemberWalletCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<NftMemberWallet> walletByMemberId(Long memberId) {
        List<NftMemberWallet> result = queryFactory.selectFrom(nftMemberWallet)
                .where(nftMemberWallet.nftMember.nftMemberId.eq(memberId))
                .fetch();
        return result;
    }
    @Override
    public NftMemberWallet walletByContractAddress(String contractAddress) {
        NftMemberWallet result = queryFactory.selectFrom(nftMemberWallet)
                .where(nftMemberWallet.walletContractAddress.eq(contractAddress))
                .fetchOne();
        return result;
    }

    @Override
    public Long updateWalletStatus(Long walletId, Long memberId, ActiveStatus activeStat) {
        Long result = queryFactory.update(nftMemberWallet)
                .set(nftMemberWallet.activeStatus, activeStat)
                .where(nftMemberWallet.nftMemberWalletId.eq(walletId)
                        , nftMemberWallet.nftMember.nftMemberId.eq(memberId))
                .execute();

        return result;
    }

    @Override
    public Long deleteWalletStatus(Long walletId, Long memberId) {
        Long result = queryFactory.update(nftMemberWallet)
                .set(nftMemberWallet.activeStatus, ActiveStatus.DELETE)
                .where(nftMemberWallet.nftMemberWalletId.eq(walletId)
                        , nftMemberWallet.nftMember.nftMemberId.eq(memberId))
                .execute();

        return result;
    }
}
