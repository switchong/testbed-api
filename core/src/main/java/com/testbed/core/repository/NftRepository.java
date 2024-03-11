package com.testbed.core.repository;

import com.testbed.core.domain.nftgram.Nft;
import com.testbed.core.repository.custom.NftCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NftRepository  extends JpaRepository<Nft, Long>, NftCustomRepository {

}