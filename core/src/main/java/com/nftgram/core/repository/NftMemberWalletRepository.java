package com.nftgram.core.repository;

import com.nftgram.core.domain.nftgram.NftMemberWallet;
import com.nftgram.core.repository.custom.NftMemberWalletCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NftMemberWalletRepository extends JpaRepository<NftMemberWallet, Long>, NftMemberWalletCustomRepository {
}
