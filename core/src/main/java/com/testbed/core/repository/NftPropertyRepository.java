package com.testbed.core.repository;

import com.testbed.core.domain.nftgram.NftProperty;
import com.testbed.core.repository.custom.NftPropertyCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NftPropertyRepository extends JpaRepository<NftProperty , Long>, NftPropertyCustomRepository {
}
