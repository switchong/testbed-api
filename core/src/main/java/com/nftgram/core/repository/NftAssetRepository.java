package com.nftgram.core.repository;

import com.nftgram.core.domain.nftgram.NftAsset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NftAssetRepository extends JpaRepository<NftAsset , Long> {
}
