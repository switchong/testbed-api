package com.nftgram.core.repository;

import com.nftgram.core.domain.nftgram.NftMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NftMemberRepository extends JpaRepository<NftMember , Long> {
}
