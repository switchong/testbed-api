package com.nftgram.core.repository;

import com.nftgram.core.domain.nftgram.Nft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NftRepository  extends JpaRepository<Nft , Long> {

    List<Nft> findAll();

    @Query(name = "Nft.findByOwnerUserName")
    List<Nft> findByOwnerUserName(@Param("ownerUserName") String username);

    @Query(name = "Nft.findByCreatorUserName")
    List<Nft> findByCreatorUserName(@Param("creatorUserName") String username);

    @Query(name = "Nft.findByName")
    List<Nft> findByName(@Param("name") String nftName);

    @Query(name = "Nft.findByAssetContractAddress")
    List<Nft> findByAssetContractAddress(@Param("assetContractAddress") String nftAddress);

    @Query(name = "Nft.findByNftId")
    Nft findByNftId(@Param("nftId") Long nftId);

}
