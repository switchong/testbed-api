package com.nftgram.core.repository;

import com.nftgram.core.domain.nftgram.Nft;
import com.nftgram.core.repository.custom.NftCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NftRepository  extends JpaRepository<Nft , Long>, NftCustomRepository {

}