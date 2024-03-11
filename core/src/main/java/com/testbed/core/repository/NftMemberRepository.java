package com.testbed.core.repository;


import com.testbed.core.domain.nftgram.NftMember;
import com.testbed.core.repository.custom.NftMemberCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
public interface NftMemberRepository extends JpaRepository<NftMember, Long>, NftMemberCustomRepository {


//   NftMember findByNftMemberUserId(String nftMemberUserId);

    boolean existsByUsername(String username);
}
