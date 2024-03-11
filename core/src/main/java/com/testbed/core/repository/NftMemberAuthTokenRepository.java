package com.testbed.core.repository;

import com.testbed.core.domain.nftgram.NftMemberAuthToken;
import com.testbed.core.repository.custom.NftMemberAuthTokenCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NftMemberAuthTokenRepository extends JpaRepository<NftMemberAuthToken, Long>, NftMemberAuthTokenCustomRepository {
}
