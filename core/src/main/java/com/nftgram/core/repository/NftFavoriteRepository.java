package com.nftgram.core.repository;

import com.nftgram.core.domain.nftgram.NftFavorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NftFavoriteRepository extends JpaRepository<NftFavorite , Long> {
}
