package com.nftgram.core.repository.custom;

import com.nftgram.core.domain.nftgram.NftCollection;

import java.util.List;

public interface NftCollectionCustomRepository {

    List<NftCollection> findAll();
}
