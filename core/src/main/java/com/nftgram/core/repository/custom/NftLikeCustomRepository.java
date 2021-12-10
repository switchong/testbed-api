package com.nftgram.core.repository.custom;

import com.nftgram.core.domain.nftgram.NftLike;

import java.util.List;

public interface NftLikeCustomRepository {

    List<NftLike> findNftLikeMemberId(Long nftId, Long nftMemberId);
}
