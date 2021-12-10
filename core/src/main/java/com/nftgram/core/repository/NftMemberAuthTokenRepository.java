package com.nftgram.core.repository;

import com.nftgram.core.domain.nftgram.NftMemberAuthToken;
import com.nftgram.core.repository.custom.NftMemberAuthTokenCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NftMemberAuthTokenRepository extends JpaRepository<NftMemberAuthToken, Long>, NftMemberAuthTokenCustomRepository {
}
