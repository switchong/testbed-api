package com.nftgram.core.repository;

import com.nftgram.core.domain.nftgram.NftMemberBackground;
import com.nftgram.core.repository.custom.NftMemberBackgroundCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NftMemberBackgroundRepository  extends JpaRepository<NftMemberBackground, Long>, NftMemberBackgroundCustomRepository {
}
