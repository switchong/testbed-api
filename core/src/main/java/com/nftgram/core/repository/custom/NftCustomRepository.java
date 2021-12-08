package com.nftgram.core.repository.custom;

import com.nftgram.core.domain.nftgram.Nft;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NftCustomRepository {

    List<Nft> findAll();

    Page<Nft> findAllPage(Pageable pageable);

    List<Nft> findAllNft(Pageable pageable);

    List<Nft> findAllNftGallery(Pageable pageable);

    List<Nft> findByNftCollectionId(Long nftCollectionId);

    List<Nft> findByOwnerUserName(String username);

    List<Nft> findByCreatorUserName(String username);

    List<Nft> findByName(String nftName);

    List<Nft> findByAssetContractAddress(String nftAddress);

    Page<Nft> findByNftId(Pageable pageable,Long nftId);

    List<Nft> findByCollectionName(String collection);

    List<Nft> findByNftCollection(Long collectionId);
}
