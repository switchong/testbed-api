package com.nftgram.core.repository.custom;

import com.nftgram.core.domain.nftgram.NftMember;

public interface NftMemberCustomRepository {

    NftMember findByNftMemberUserId(String nftMemberUserId);
}
