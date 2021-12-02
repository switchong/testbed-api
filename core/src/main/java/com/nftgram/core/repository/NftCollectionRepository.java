package com.nftgram.core.repository;

import com.nftgram.core.domain.nftgram.NftCollection;
import com.nftgram.core.repository.custom.NftCollectionCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NftCollectionRepository extends JpaRepository<NftCollection , Long>, NftCollectionCustomRepository {

    List<NftCollection> findAll();

    @Query
    List<NftCollection> findByNftCollectionId(@Param("nftCollectionId") Long nftCollectionId);

    @Query(name = "NftCollection.findNftCollectionByCollectionSlug")
    List<NftCollection> findNftCollectionByCollectionSlug(@Param("collectionSlug") String collectionSlug);

}