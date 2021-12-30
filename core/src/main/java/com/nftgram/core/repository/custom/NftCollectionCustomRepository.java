package com.nftgram.core.repository.custom;

import com.nftgram.core.domain.nftgram.Nft;
import com.nftgram.core.domain.nftgram.NftCollection;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NftCollectionCustomRepository {

    List<NftCollection> findAll();

    List<Nft> findAllNftGallery(Pageable pageable, Long memberId);

    List<Nft> findAllNftGalleryLike(Pageable pageable, Long memberId);
}
