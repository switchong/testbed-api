package com.testbed.core.repository;

import com.testbed.core.domain.nftgram.NftCollection;
import com.testbed.core.repository.custom.NftCollectionCustomRepository;
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