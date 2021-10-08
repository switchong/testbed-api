package com.nftgram.core.repository;

import com.nftgram.core.domain.nftgram.NftCollection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NftCollectionRepository extends JpaRepository<NftCollection , Long> {
}
