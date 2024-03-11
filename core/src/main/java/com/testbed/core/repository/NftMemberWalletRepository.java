package com.testbed.core.repository;

import com.testbed.core.domain.nftgram.NftMemberWallet;
import com.testbed.core.repository.custom.NftMemberWalletCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NftMemberWalletRepository extends JpaRepository<NftMemberWallet, Long>, NftMemberWalletCustomRepository {
}
