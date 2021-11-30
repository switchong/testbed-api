package com.nftgram.core.repository;

import com.nftgram.core.domain.token.Token;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NftMemberTokenRepository  extends JpaRepository<Token , Long> {
}
