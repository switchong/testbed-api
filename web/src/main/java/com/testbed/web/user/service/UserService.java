package com.testbed.web.user.service;

import com.testbed.core.domain.testbed.Nft;
import com.testbed.core.domain.testbed.NftMember;
import com.testbed.core.repository.NftAssetRepository;
import com.testbed.core.repository.NftCollectionRepository;
import com.testbed.core.repository.NftMemberRepository;
import com.testbed.core.repository.NftRepository;
import com.testbed.web.common.dto.GalleryMemberDto;
import com.testbed.web.common.dto.response.CommonNftResponse;
import com.testbed.web.common.service.NftFindService;
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

    public NftMember findNftUserMemberId(Pageable pageable, Long memberId, Long url_member_id, String page) {

        NftMember urlNftMember = nftMemberRepository.findByNftMemberId(url_member_id);

        return urlNftMember;
    }

    public NftMember findNftUsernameMember(Pageable pageable, Long memberId, String username, String page) {

        NftMember urlNftMember = nftMemberRepository.findNftUsername(username);

        return urlNftMember;
    }

    public GalleryMemberDto findNftUsername(Pageable pageable, String keyword, String username) {
        NftMember urlNftMember = NftMember.builder().build();

        List<Nft> galleryUserList = nftRepository.findNftUsername(pageable, keyword, username);

        List<CommonNftResponse> nftResponse = new ArrayList<>();

        Long sliderCount = Long.valueOf(0);

        List<List<CommonNftResponse>> sliderResponse =  new ArrayList<>();

        if(galleryUserList != null) {

            nftResponse = nftFindService.setCommonNftResponses(galleryUserList);

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
