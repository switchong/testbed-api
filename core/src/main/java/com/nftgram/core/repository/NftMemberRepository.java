package com.nftgram.core.repository;


import com.nftgram.core.domain.dto.NftMemberDto;
import com.nftgram.core.domain.nftgram.NftMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NftMemberRepository extends JpaRepository<NftMember , Long> {



   Optional<NftMember> findByNftMemberUserId(String nftMemberUserId);

   //NftMemberDto findByNftMemberUserId(String nftMemberUserId);




}
