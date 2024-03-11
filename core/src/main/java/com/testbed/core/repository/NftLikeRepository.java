package com.testbed.core.repository;

import com.testbed.core.domain.nftgram.NftLike;
import com.testbed.core.repository.custom.NftLikeCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NftLikeRepository extends JpaRepository<NftLike , Long> , NftLikeCustomRepository {
}
