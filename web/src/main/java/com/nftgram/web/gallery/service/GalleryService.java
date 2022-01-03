package com.nftgram.web.gallery.service;

import com.nftgram.core.domain.nftgram.Nft;
import com.nftgram.core.domain.nftgram.NftMember;
import com.nftgram.core.repository.NftAssetRepository;
import com.nftgram.core.repository.NftCollectionRepository;
import com.nftgram.core.repository.NftMemberRepository;
import com.nftgram.core.repository.NftRepository;
import com.nftgram.web.common.dto.response.CommonNftResponse;
import com.nftgram.web.common.service.NftFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class GalleryService {
    private final NftFindService nftFindService;

    private final NftRepository nftRepository;
    private final NftMemberRepository nftMemberRepository;
    private final NftAssetRepository nftAssetRepository;
    private final NftCollectionRepository nftCollectionRepository;

    private CommonNftResponse commonNftResponse;

    private List<CommonNftResponse> commonNftResponses = new ArrayList<>();

    public List<Nft> findByCollectionName(String collection) {
        List<Nft> nftInfo = nftRepository.findByNftCollectionName(collection);

        return nftInfo;
    }

    public List<CommonNftResponse> findAllNftGallery(Pageable pageable) {
        List<Nft> GalleryList = nftRepository.findAllNftGallery(pageable);

        List<CommonNftResponse> response = new ArrayList<>();

        commonNftResponses = nftFindService.setCommonNftResponses(GalleryList);

        return commonNftResponses;
    }

    public List<CommonNftResponse> findAllNftGalleryMember(Pageable pageable, Long memberId) {
        NftMember nftMember = nftMemberRepository.findByNftMemberId(memberId);

        List<Nft> galleryList = nftCollectionRepository.findAllNftGallery(pageable, memberId);

        return nftFindService.setCommonNftResponses(galleryList);
    }

    public List<CommonNftResponse> findAllNftGalleryMemberLike(Pageable pageable, Long memberId) {
        NftMember nftMember = nftMemberRepository.findByNftMemberId(memberId);

        List<Nft> galleryLikeList = nftCollectionRepository.findAllNftGalleryLike(pageable, memberId);

        return nftFindService.setCommonNftResponses(galleryLikeList);
    }
}
