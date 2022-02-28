package com.nftgram.core.repository.custom;

import com.nftgram.core.domain.nftgram.Nft;
import com.nftgram.core.dto.NftCommonDto;
import com.nftgram.core.dto.NftIdWalletList;
import com.nftgram.core.dto.NftOneJoinDto;
import com.nftgram.core.dto.request.NftGalleryRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NftCustomRepository {

    List<Nft> findAll();

    Page<Nft> findAllPage(Pageable pageable);


    List<Nft> findAllNft(Pageable pageable, String keyword , Long sort  );


    List<Nft> findNftUsername(Pageable pageable, String keyword, String username);

    List<Nft> findAllNftGallery(Pageable pageable);

    List<Nft> findByNftCollectionName(String collection);

    List<Nft> findByNftCollectionId(Pageable pageable, Long nftCollectionId);

    NftOneJoinDto findByNftIdOne(Long nftId);

    List<Nft> findByNftLikeMember(Pageable pageable, Long nftMemberId);

    List<Nft> findByNftMemberList(Pageable pageable, Long nftMemberId);

    List<Nft> findByNftMemberEditList(Pageable pageable, Long nftMemberId);

    List<Nft> findByNftMemberEditBgList(Pageable pageable, Long nftMemberId);

    Long updateNftViewCount(Long nftId);

    Long countNftViewCount(Long nftId);

    Long countNftLikeCount(Long nftId);

    void updateNftLikeCountPuls(Long nftId);

    void updateNftLikeCountMinus(Long nftId);

    List<NftIdWalletList> findNftContractAddress(String walletAddress);

    Long updateNftMemberWallet(List<Long> nftIdArr, Long memberId);

    NftCommonDto findAllNftCommon(Pageable pageable, NftGalleryRequest nftGalleryRequest);

    Long updateNftBackground(Long memberId, Long nftId, Long sectionSeq);

    Long updateNftOrderSeq(Long memberId, Long nftId, Long orderSeq);

    Long updateNftFrame(Long memberId, Long nftId, Long frameNftId);
}
