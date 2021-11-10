package com.nftgram.core.repository;


import com.nftgram.core.domain.member.MemberStatus;
import com.nftgram.core.domain.nftgram.NftMember;
import org.springframework.data.jpa.repository.JpaRepository;




import java.util.Optional;
public interface NftMemberRepository extends JpaRepository<NftMember , Long> {


   //@Query("select n from NftMember n where n.nftMemberUserId = ?1")
   Optional<NftMember> findByNftMemberUserIdAndMemberStatus(String nftMemberUserId, MemberStatus nftMemberStatus);

}
