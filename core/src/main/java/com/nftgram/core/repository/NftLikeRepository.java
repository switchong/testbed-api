package com.nftgram.core.repository;

import com.nftgram.core.domain.nftgram.NftLike;
import com.nftgram.core.repository.custom.NftLikeCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NftLikeRepository extends JpaRepository<NftLike , Long> , NftLikeCustomRepository {
}
