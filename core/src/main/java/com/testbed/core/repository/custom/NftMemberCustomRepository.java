package com.testbed.core.repository.custom;

import com.testbed.core.domain.nftgram.NftMember;
import com.testbed.core.dto.request.NftMemberRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NftMemberCustomRepository {

    NftMember findByNftMemberUserId(String nftMemberUserId);

    NftMember findByNftMemberId(Long nftMemberId);

    Long updateNftMember(NftMemberRequestDto nftMemberUpdate, Long nftMemberId);

    Page<NftMember> findByNftMemberList(Pageable pageable , String keyword );

    NftMember findNftUsername(String username);

    boolean findNftMemberBackgroundFlag(Long memberId);

    NftMember findNftMemberWalletAddress(String address);
}
