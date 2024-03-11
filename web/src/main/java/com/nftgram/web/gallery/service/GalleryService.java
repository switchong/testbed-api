package com.nftgram.web.gallery.service;

import com.testbed.core.domain.nftgram.Nft;
import com.testbed.core.domain.nftgram.NftCollection;
import com.testbed.core.domain.nftgram.NftMember;
import com.testbed.core.dto.request.NftMemberRequestDto;
import com.testbed.core.repository.NftAssetRepository;
import com.testbed.core.repository.NftCollectionRepository;
import com.testbed.core.repository.NftMemberRepository;
import com.testbed.core.repository.NftRepository;
import com.nftgram.web.common.dto.GalleryDto;
import com.nftgram.web.common.dto.GalleryMemberDto;
import com.nftgram.web.common.dto.response.CommonNftResponse;
import com.nftgram.web.common.service.NftFindService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import java.text.ParseException;
import java.util.*;

@RequiredArgsConstructor
@Service
@Slf4j
public class GalleryService {
    private final NftFindService nftFindService;

    private final NftRepository nftRepository;
    private final NftMemberRepository nftMemberRepository;
    private final NftAssetRepository nftAssetRepository;
    private final NftCollectionRepository nftCollectionRepository;



    private CommonNftResponse commonNftResponse;

    private List<CommonNftResponse> commonNftResponses = new ArrayList<>();
    private EntityManagerFactory entityManagerFactory;

    public List<Nft> findByCollectionName(String collection) {
        List<Nft> nftInfo = nftRepository.findByNftCollectionName(collection);

        return nftInfo;
    }

    public List<CommonNftResponse> findAllNftGallery(Pageable pageable) {
        List<Nft> GalleryList = nftRepository.findAllNftGallery(pageable);

        commonNftResponses = nftFindService.setCommonNftResponses(GalleryList);

        return commonNftResponses;
    }

    public GalleryDto findAllNFTList(Pageable pageable, String keyword, Long sort) throws ParseException {

        NftCollection collection = NftCollection.builder().build();

        List<CommonNftResponse> nftResponse = nftFindService.findAllList(pageable, keyword, sort);

        Long sliderCount = Long.valueOf((long) Math.ceil(nftResponse.size()/(3 * 1.0)));

        List<List<CommonNftResponse>> sliderResponse = nftFindService.nftResponseList(sliderCount, nftResponse);

        GalleryDto galleryDto = GalleryDto.builder()
                .collection(collection)
                .slideList(sliderResponse)
                .galleryList(nftResponse)
                .build();

        return galleryDto;
    }

    public GalleryMemberDto findAllNftGalleryMemberLike(Pageable pageable, Long memberId ) {
        NftMember nftMember = nftMemberRepository.findByNftMemberId(memberId);

        List<Nft> galleryLikeList = nftCollectionRepository.findAllNftGalleryLike(pageable, memberId);

        List<CommonNftResponse> nftResponse = nftFindService.setCommonNftResponses(galleryLikeList);

        Long sliderCount = Long.valueOf((long) Math.ceil(nftResponse.size()/(3 * 1.0)));

        List<List<CommonNftResponse>> sliderResponse = nftFindService.nftResponseList(sliderCount, nftResponse);

        GalleryMemberDto galleryMemberDto = GalleryMemberDto.builder()
                .sliderCount(sliderCount)
                .nftMember(nftMember)
                .nftSliderList(sliderResponse)
                .nftResponseList(nftResponse)
                .build();

        return galleryMemberDto;
    }

    public GalleryMemberDto findAllNftGalleryMember(Pageable pageable, Long memberId ) {
        NftMember nftMember = nftMemberRepository.findByNftMemberId(memberId);

        List<Nft> galleryList = nftCollectionRepository.findAllNftGallery(pageable, memberId);

        List<CommonNftResponse> nftResponse = nftFindService.setCommonNftResponses(galleryList);

        Long sliderCount = Long.valueOf((long) Math.ceil(nftResponse.size()/(3 * 1.0)));

        List<List<CommonNftResponse>> sliderResponse = nftFindService.nftResponseList(sliderCount, nftResponse);

        GalleryMemberDto galleryMemberDto = GalleryMemberDto.builder()
                .sliderCount(sliderCount)
                .nftMember(nftMember)
                .nftResponseList(nftResponse)
                .nftSliderList(sliderResponse)
                .build();

        return galleryMemberDto;
    }

    public int lastNum(int num, int size) {
        if(size < num) {
            return size;
        }
        return num;
    }
    @Transactional
    public Long nftMemberUpdate(NftMemberRequestDto update , Long nftMemberId){
        Long isResult = Long.valueOf(1);
        checkUsername(update.getUsername());
        NftMember nftMember1 = nftMemberRepository.findByNftMemberId(nftMemberId);

        Long nftMember = nftMemberRepository.updateNftMember(update , nftMemberId);

        return isResult;
    }



    public boolean checkUsername(String username){
        return nftMemberRepository.existsByUsername(username);
    }
}