package com.testbed.core.repository.custom;

import com.testbed.core.domain.common.value.ActiveStatus;
import com.testbed.core.domain.nftgram.NftLike;

import java.util.List;

public interface NftLikeCustomRepository {

    List<NftLike> findByLikeNftMemberId(Long nftId, Long nftMemberId);

    List<NftLike> findByLikeNftId(Long nftId);

    NftLike findByLikeMemberId(Long nftMemberId);

    NftLike findByLikeMemberIdOne(Long nftId, Long nftMemberId);

    void deleteByLikeMember(Long likeId, ActiveStatus activeStatus);
}
