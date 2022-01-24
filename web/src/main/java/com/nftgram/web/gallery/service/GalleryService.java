package com.nftgram.web.gallery.service;

import com.nftgram.core.domain.nftgram.Nft;
import com.nftgram.core.domain.nftgram.NftMember;
import com.nftgram.core.repository.NftAssetRepository;
import com.nftgram.core.repository.NftCollectionRepository;
import com.nftgram.core.repository.NftMemberRepository;
import com.nftgram.core.repository.NftRepository;
import com.nftgram.web.common.dto.GalleryMemberDto;
import com.nftgram.web.common.dto.response.CommonNftResponse;
import com.nftgram.web.common.dto.response.CommonNftSlider;
import com.nftgram.web.common.service.NftFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
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

        commonNftResponses = nftFindService.setCommonNftResponses(GalleryList);

        return commonNftResponses;
    }

    public List<List<CommonNftResponse>> findAllNFTList(Pageable pageable, String keyword, Long sort) throws ParseException {

        List<List<CommonNftResponse>> slideList = new ArrayList<>();
        List<CommonNftResponse> nftResponse = nftFindService.findAllList(pageable, keyword, sort);

        Long sliderCount = Long.valueOf((long) Math.ceil(nftResponse.size()/(3 * 1.0)));

        for(int i = 0;i<sliderCount;i++) {
            System.out.println(sliderCount);
            int idx = i%3;  // 1차 배열 index 값
            int k = i * 3;  // fromIndex 시작 index값
            List<CommonNftResponse> nftResult = nftResponse.subList(k,(lastNum(k+3, nftResponse.size())));   // fromIndex , toIndex(미만)
//            List<CommonNftSlider> slider = CommonNftSlider.builder()
//                    .nftResponseList(nftResult)
//                    .build();

            slideList.add(nftResult);
        }

        return slideList;
    }

    public GalleryMemberDto findAllNftGalleryMemberLike(Pageable pageable, Long memberId) {
        NftMember nftMember = nftMemberRepository.findByNftMemberId(memberId);

        List<Nft> galleryLikeList = nftCollectionRepository.findAllNftGalleryLike(pageable, memberId);

        List<List<CommonNftResponse>> sliderResponse = new ArrayList<>();

        List<CommonNftResponse> nftResponse = nftFindService.setCommonNftResponses(galleryLikeList);

        Long sliderCount = Long.valueOf((long) Math.ceil(nftResponse.size()/(3 * 1.0)));

        for(int i = 0;i<sliderCount;i++) {
            System.out.println(sliderCount);
            int idx = i%3;  // 1차 배열 index 값
            int k = i * 3;  // fromIndex 시작 index값
            List<CommonNftResponse> nftResult = nftResponse.subList(k,(lastNum(k+3, nftResponse.size())));   // fromIndex , toIndex(미만)
//            List<CommonNftSlider> slider = CommonNftSlider.builder()
//                    .nftResponseList(nftResult)
//                    .build();

            sliderResponse.add(nftResult);
        }

        GalleryMemberDto galleryMemberDto = GalleryMemberDto.builder()
                .sliderCount(sliderCount)
                .nftMember(nftMember)
                .nftSliderList(sliderResponse)
                .nftResponseList(nftResponse)
                .build();

        System.out.println( "galleryMemberDto : " + galleryMemberDto.getSliderCount() + "/" + nftResponse.size());

        return galleryMemberDto;
    }

    public GalleryMemberDto findAllNftGalleryMember(Pageable pageable, Long memberId) {
        NftMember nftMember = nftMemberRepository.findByNftMemberId(memberId);

        List<Nft> galleryList = nftCollectionRepository.findAllNftGallery(pageable, memberId);
        List<List<CommonNftResponse>> sliderResponse = new ArrayList<>();
        List<CommonNftResponse> nftResponse = nftFindService.setCommonNftResponses(galleryList);

        Long slideCnt = Long.valueOf((long) Math.ceil(nftResponse.size()/(3 * 1.0)));

        for(int i = 0;i<slideCnt;i++) {
            System.out.println(slideCnt);
            int idx = i%3;  // 1차 배열 index 값
            int k = i * 3;  // fromIndex 시작 index값
            List<CommonNftResponse> nftResult = nftResponse.subList(k,(lastNum(k+3, nftResponse.size())));   // fromIndex , toIndex(미만)
//            List<CommonNftSlider> slider = CommonNftSlider.builder()
//                    .nftResponseList(nftResult)
//                    .build();

            sliderResponse.add(nftResult);
        }

        GalleryMemberDto galleryMemberDto = GalleryMemberDto.builder()
                .sliderCount(slideCnt)
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
}
