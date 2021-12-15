package com.nftgram.core.repository.custom;

import com.nftgram.core.domain.common.value.ActiveStatus;
import com.nftgram.core.domain.nftgram.NftLike;

import java.util.List;

public interface NftLikeCustomRepository {

    List<NftLike> findByLikeNftMemberId(Long nftId, Long nftMemberId);

    List<NftLike> findByLikeNftId(Long nftId);

    NftLike findByLikeMemberId(Long nftMemberId);

    NftLike findByLikeMemberIdOne(Long nftId, Long nftMemberId);

    void deleteByLikeMember(Long likeId, ActiveStatus activeStatus);
}
