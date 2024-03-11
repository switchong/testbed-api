package com.testbed.core.repository.custom;

import com.testbed.core.domain.common.value.ActiveStatus;
import com.testbed.core.domain.nftgram.NftMemberWallet;

import java.util.List;

public interface NftMemberWalletCustomRepository {

    List<NftMemberWallet> walletByMemberId(Long memberId);

    NftMemberWallet walletByContractAddress(String contractAddress);

    Long updateWalletStatus(Long walletId, Long memberId, ActiveStatus activeStat);

    Long deleteWalletStatus(Long walletId, Long memberId);
}
