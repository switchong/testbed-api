package com.nftgram.core.repository;


import com.nftgram.core.domain.nftgram.NftMember;
import com.nftgram.core.repository.custom.NftMemberCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
public interface NftMemberRepository extends JpaRepository<NftMember , Long>, NftMemberCustomRepository {


//   NftMember findByNftMemberUserId(String nftMemberUserId);

    boolean existsByUsername(String username);
}
