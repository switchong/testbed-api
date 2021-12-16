package com.nftgram.core.repository.custom;

import com.nftgram.core.domain.nftgram.NftMemberWallet;

import java.util.List;

public interface NftMemberWalletCustomRepository {

    List<NftMemberWallet> walletByMemberId(Long memberId);
}
