package com.nftgram.core.repository.custom;

import com.nftgram.core.domain.nftgram.Nft;
import com.nftgram.core.dto.NftOneJoinDto;
import com.nftgram.core.dto.SearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface NftCustomRepository {

    List<Nft> findAll();

    Page<Nft> findAllPage(Pageable pageable);

    //List<Nft> findAllNft(Pageable pageable , String keyword);


    List<Nft> findAllNft(Pageable pageable, String keyword , String sort  );

    List<Nft> findAllNftGallery(Pageable pageable);

    List<Nft> findByNftCollectionName(String collection);

    List<Nft> findByNftCollectionId(Long nftCollectionId);


    NftOneJoinDto findByNftIdOne(Long nftId);

    List<Nft> findByNftLikeMember(Pageable pageable, Long nftMemberId);

    Long updateNftViewCount(Long nftId);

    Long countNftViewCount(Long nftId);

    Long countNftLikeCount(Long nftId);

    void updateNftLikeCountPuls(Long nftId);

    void updateNftLikeCountMinus(Long nftId);


}
