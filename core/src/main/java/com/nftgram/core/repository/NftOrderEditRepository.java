package com.nftgram.core.repository;

import com.nftgram.core.domain.nftgram.NftOrderEdit;
import com.nftgram.core.repository.custom.NftOrderEditCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NftOrderEditRepository  extends JpaRepository<NftOrderEdit, Long>, NftOrderEditCustomRepository {
}
