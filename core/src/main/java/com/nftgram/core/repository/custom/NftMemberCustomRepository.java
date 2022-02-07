package com.nftgram.core.repository.custom;

import com.nftgram.core.domain.nftgram.NftMember;
import com.nftgram.core.dto.request.NftMemberRequestDto;

public interface NftMemberCustomRepository {

    NftMember findByNftMemberUserId(String nftMemberUserId);

    NftMember findByNftMemberId(Long nftMemberId);

    Long updateNftMember(NftMemberRequestDto nftMemberUpdate, Long nftMemberId);


    NftMember findNftUsername(String username);

    NftMember findNftMemberWalletAddress(String address);
}
