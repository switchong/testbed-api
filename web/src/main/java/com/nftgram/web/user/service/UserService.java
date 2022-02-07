package com.nftgram.web.user.service;

import com.nftgram.core.domain.nftgram.Nft;
import com.nftgram.core.domain.nftgram.NftMember;
import com.nftgram.core.repository.NftAssetRepository;
import com.nftgram.core.repository.NftCollectionRepository;
import com.nftgram.core.repository.NftMemberRepository;
import com.nftgram.core.repository.NftRepository;
import com.nftgram.web.common.dto.GalleryMemberDto;
import com.nftgram.web.common.dto.response.CommonNftResponse;
import com.nftgram.web.common.service.NftFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final NftFindService nftFindService;

    private final NftRepository nftRepository;
    private final NftMemberRepository nftMemberRepository;
    private final NftAssetRepository nftAssetRepository;
    private final NftCollectionRepository nftCollectionRepository;

    public GalleryMemberDto findNftUsername(Pageable pageable, Long memberId, Long url_member_id) {
        NftMember urlNftMember = nftMemberRepository.findByNftMemberId(url_member_id);

        List<Nft> galleryLikeList = new ArrayList<>();

        List<CommonNftResponse> nftResponse = new ArrayList<>();

        Long sliderCount = Long.valueOf(0);

        List<List<CommonNftResponse>> sliderResponse =  new ArrayList<>();

        if(urlNftMember != null) {
            galleryLikeList = nftRepository.findByNftMemberList(pageable, urlNftMember.getNftMemberId());

            nftResponse = nftFindService.setCommonNftResponses(galleryLikeList);

            sliderCount = Long.valueOf((long) Math.ceil(nftResponse.size()/(3 * 1.0)));

            sliderResponse = nftFindService.nftResponseList(sliderCount, nftResponse);
        }


        GalleryMemberDto galleryMemberDto = GalleryMemberDto.builder()
                .sliderCount(sliderCount)
                .nftMember(urlNftMember)
                .nftSliderList(sliderResponse)
                .nftResponseList(nftResponse)
                .build();

        return galleryMemberDto;
    }

    public GalleryMemberDto findNftMemberAddress(Pageable pageable, Long memberId, String address) {
        NftMember urlNftMember = nftMemberRepository.findNftMemberWalletAddress(address);

        List<Nft> galleryLikeList = new ArrayList<>();

        List<CommonNftResponse> nftResponse = new ArrayList<>();

        Long sliderCount = Long.valueOf(0);

        List<List<CommonNftResponse>> sliderResponse =  new ArrayList<>();

        if(urlNftMember != null) {
            galleryLikeList = nftRepository.findByNftMemberList(pageable, urlNftMember.getNftMemberId());

            nftResponse = nftFindService.setCommonNftResponses(galleryLikeList);

            sliderCount = Long.valueOf((long) Math.ceil(nftResponse.size()/(3 * 1.0)));

            sliderResponse = nftFindService.nftResponseList(sliderCount, nftResponse);
        }


        GalleryMemberDto galleryMemberDto = GalleryMemberDto.builder()
                .sliderCount(sliderCount)
                .nftMember(urlNftMember)
                .nftSliderList(sliderResponse)
                .nftResponseList(nftResponse)
                .build();

        return galleryMemberDto;
    }
}
