package com.testbed.core.repository;

import com.testbed.core.domain.nftgram.NftFavorite;
import com.testbed.core.repository.custom.NftFavoriteCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NftFavoriteRepository extends JpaRepository<NftFavorite , Long>, NftFavoriteCustomRepository {
}
