package com.testbed.core.repository;

import com.testbed.core.domain.nftgram.NftAsset;
import com.testbed.core.repository.custom.NftAssetCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NftAssetRepository extends JpaRepository<NftAsset , Long>, NftAssetCustomRepository {
}
