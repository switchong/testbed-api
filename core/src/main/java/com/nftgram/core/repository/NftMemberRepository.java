package com.nftgram.core.repository;


import com.nftgram.core.domain.member.MemberStatus;
import com.nftgram.core.domain.nftgram.NftMember;
import org.springframework.data.jpa.repository.JpaRepository;



import java.util.Optional;
public interface NftMemberRepository extends JpaRepository<NftMember , Long> {

   NftMember findByNftMemberUserId(String nftMemberUserId);


}
