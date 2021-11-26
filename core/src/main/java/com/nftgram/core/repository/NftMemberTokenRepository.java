package com.nftgram.core.repository;

import com.nftgram.core.domain.nftgram.NftMemberAuthToken;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NftMemberTokenRepository  extends JpaRepository<NftMemberAuthToken, Long> {
}
