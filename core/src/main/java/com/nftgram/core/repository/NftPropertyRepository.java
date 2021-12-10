package com.nftgram.core.repository;

import com.nftgram.core.domain.nftgram.NftProperty;
import com.nftgram.core.repository.custom.NftPropertyCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NftPropertyRepository extends JpaRepository<NftProperty , Long>, NftPropertyCustomRepository {
}
