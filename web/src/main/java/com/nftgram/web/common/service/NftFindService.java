package com.nftgram.web.common.service;

import com.nftgram.core.domain.nftgram.Nft;
import com.nftgram.core.domain.nftgram.NftCollection;
import com.nftgram.core.domain.nftgram.NftLike;
import com.nftgram.core.domain.nftgram.NftMember;
import com.nftgram.core.dto.NftCommonDto;
import com.nftgram.core.dto.NftOneJoinDto;
import com.nftgram.core.dto.request.NftGalleryRequest;
import com.nftgram.core.repository.*;
import com.nftgram.web.api.dto.response.GetNftOneResponse;
import com.nftgram.web.common.dto.GalleryDto;
import com.nftgram.web.common.dto.NftGalleryCommonDto;
import com.nftgram.web.common.dto.response.CommonNftResponse;
import com.nftgram.web.common.dto.response.NftPropertyResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static com.nftgram.core.domain.common.value.ActiveStatus.ACTIVE;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class NftFindService {
    private CommonNftResponse commonNftResponse;
    private final NftRepository nftRepository;
    private final NftMemberRepository nftMemberRepository;
    private final NftAssetRepository nftAssetRepository;
    private final NftCollectionRepository nftCollectionRepository;
    private final NftLikeRepository nftLikeRepository;
    private final NftPropertyRepository nftPropertyRepository;

    private List<CommonNftResponse> commonNftResponses = new ArrayList<>();

    /**
     * 메인 NFT 리스트 검색
     * @param pageable
     * @return
     * @throws ParseException
     */
    @Transactional(readOnly = true)
    public List<CommonNftResponse> findAllList(Pageable pageable , String keyword , Long sort  )  throws ParseException {
        List<Nft> nftRepositoryAll = nftRepository.findAllNft(pageable, keyword , sort );

        List<CommonNftResponse> response = setCommonNftResponses(nftRepositoryAll);

        return response;
    }

    /**
     * NFT 갤러리 collection_id 조회하여 검색
     * @param collectionId
     * @return
     */
    @Transactional(readOnly = true)
    public GalleryDto findByNftCollectionId(Pageable pageable, Long collectionId) {
        NftCollection collection = nftCollectionRepository.findNftCollection(collectionId);
        List<Nft> galleryList = nftRepository.findByNftCollectionId(pageable, collectionId);

        List<CommonNftResponse> nftResponse = setCommonNftResponses(galleryList);

        Long sliderCount = Long.valueOf((long) Math.ceil(nftResponse.size()/(3 * 1.0)));

        List<List<CommonNftResponse>> sliderResponse = nftResponseList(sliderCount, nftResponse);

        GalleryDto galleryDto = GalleryDto.builder()
                .collection(collection)
                .slideList(sliderResponse)
                .build();

        return galleryDto;
    }

    /**
     *
     * @param pageable
     * @param nftGalleryRequest ( pageType , cid , memberId , username , address , userno )
     * @return
     * @throws ParseException
     */
    public NftGalleryCommonDto nftGalleryCommonData(Pageable pageable , NftGalleryRequest nftGalleryRequest) throws ParseException, NullPointerException {
        Long totals = Long.valueOf(0);
        boolean sortFlag = false;   // Nft.orderSeq 정렬 사용 시
        NftCommonDto commonDto = new NftCommonDto();
        NftMember memberInfo = NftMember.builder().build();
        NftCollection collection = NftCollection.builder().build();
        List<Nft> nftList = new ArrayList<>();
        Long collectionId = (nftGalleryRequest.getCid() != null) ? nftGalleryRequest.getCid() : Long.valueOf(0);

        switch (nftGalleryRequest.getPageType()) {
            case "gallery" :
                collection = nftCollectionRepository.findNftCollection(collectionId);
                nftList = nftRepository.findByNftCollectionId(pageable, collectionId);
                break;
            case "user" : // case "myfavorite" :
                if(nftGalleryRequest.getUserno() != null) {
                    memberInfo = nftMemberRepository.findByNftMemberId(nftGalleryRequest.getUserno());
                }

                if(nftGalleryRequest.getLikeFlag().equals("Y")) {
                    nftList = nftRepository.findByNftLikeMember(pageable, memberInfo.getNftMemberId());
                } else {
                    nftList = nftRepository.findByNftMemberList(pageable, memberInfo.getNftMemberId());
                }
                break;
            case "username" :
                memberInfo = nftMemberRepository.findNftUsername(nftGalleryRequest.getUsername());

                if(nftGalleryRequest.getLikeFlag().equals("Y")) {
                    nftList = nftRepository.findByNftLikeMember(pageable, memberInfo.getNftMemberId());
                } else {
                    nftList = nftRepository.findByNftMemberList(pageable, memberInfo.getNftMemberId());
                }
                break;
            case "address" :
                memberInfo = nftMemberRepository.findNftMemberWalletAddress(nftGalleryRequest.getAddress());

                if(nftGalleryRequest.getLikeFlag().equals("Y")) {
                    nftList = nftRepository.findByNftLikeMember(pageable, memberInfo.getNftMemberId());
                } else {
                    nftList = nftRepository.findByNftMemberList(pageable, memberInfo.getNftMemberId());
                }
                break;
            case "externaluname" :
                nftList = nftRepository.findNftUsername(pageable, nftGalleryRequest.getKeyword(), nftGalleryRequest.getUsername());
                break;
            case "mycollection" :
                if(nftGalleryRequest.getMemberId() != null) {
                    memberInfo = nftMemberRepository.findByNftMemberId(nftGalleryRequest.getMemberId());
                }
                nftList = nftCollectionRepository.findAllNftGallery(pageable, memberInfo.getNftMemberId());
                break;
            case "edit" :
            case "test" :
            case "editNotVideo" :
                if(nftGalleryRequest.getMemberId() != null) {
                    memberInfo = nftMemberRepository.findByNftMemberId(nftGalleryRequest.getMemberId());
                }

                sortFlag = true;
                commonDto = nftRepository.findAllNftCommon(pageable, nftGalleryRequest);
                totals = commonDto.getTotals();
                nftList = commonDto.getNftList();
                break;
            case "all":
                nftList = nftRepository.findAllNft(pageable, nftGalleryRequest.getKeyword() , nftGalleryRequest.getSort() );
                break;
        }

        List<CommonNftResponse> nftResponseList = setCommonNftResponses(nftList);
        Long sliderCount = Long.valueOf((long) Math.ceil(nftResponseList.size()/(3 * 1.0)));

        List<List<CommonNftResponse>> sliderResponseList = nftResponseList(sliderCount, nftResponseList);

        NftGalleryCommonDto nftGalleryCommonResponse = NftGalleryCommonDto.builder()
                .sortFlag(sortFlag)
                .total(totals)
                .sliderCount(sliderCount)
                .nftListCount(Long.valueOf(nftResponseList.size()))
                .member(memberInfo)
                .collection(collection)
                .nftList(nftResponseList)
                .nftSliderList(sliderResponseList)
                .build();

        return nftGalleryCommonResponse;
    }

    /**
     * NFT repository 데이터 공통 파싱 ( nftFindService.getNftOneResponse() 같이 변경)
     * @param nftList
     * @return
     */
    public List<CommonNftResponse> setCommonNftResponses(List<Nft> nftList) {
        List<CommonNftResponse> response = new ArrayList<>();

        nftList.forEach(nftInfo -> {
            CommonNftResponse commonNftResponse = getCommonNftResponse(nftInfo);

            response.add(commonNftResponse);
        });

        return response;
    }


    public List<List<CommonNftResponse>> nftResponseList(Long sliderCount, List<CommonNftResponse> nftResponse) {

        List<List<CommonNftResponse>> sliderResponse = new ArrayList<>();

        for(int i = 0;i<sliderCount;i++) {
            int idx = i%3;  // 1차 배열 index 값
            int k = i * 3;  // fromIndex 시작 index값
            List<CommonNftResponse> nftResult = nftResponse.subList(k,(lastNum(k+3, nftResponse.size())));   // fromIndex , toIndex(미만)

            sliderResponse.add(nftResult);
        }

        return sliderResponse;
    }

    /**
     * NFT 정보 ( NftFindService.setCommonNftResponses() 같이 변경)
     * @param nftId
     * @param nftMemberId
     * @return
     */
    @Transactional(readOnly = true)
    public GetNftOneResponse getNftOneResponse(Long nftId, Long nftMemberId) {
        // MEMBER NFT LIKE CHECK
        String likeFlag = "N";
        if(nftMemberId > 0) {
            NftLike nftLikeRepositories = nftLikeRepository.findByLikeMemberIdOne(nftId, nftMemberId);
            if(nftLikeRepositories != null) {
                if(nftLikeRepositories.getActiveStatus() == ACTIVE){
                    likeFlag = "Y";
                }
            }
        }
        // Property List
        List<NftPropertyResponse> propertyResponse = new ArrayList<>();
        nftPropertyRepository.findByPropertiesNftId(nftId).forEach(prop -> {
            NftPropertyResponse propResult = NftPropertyResponse.builder()
                    .propId(prop.getPropId())
                    .nftId(prop.getNft().getNftId())
                    .traitType(prop.getTraitType())
                    .traitValue(prop.getTraitValue())
                    .orderCount(prop.getOrderCount())
                    .build();
            propertyResponse.add(propResult);
        });

        // NFT INFO
        NftOneJoinDto nftResponse = nftRepository.findByNftIdOne(nftId);

        CommonNftResponse commonNftResponse = getCommonNftResponse(nftResponse.getNft());

        GetNftOneResponse response = GetNftOneResponse.builder()
                .likeFlag(likeFlag)
                .nftId(commonNftResponse.getNftId())
                .name(commonNftResponse.getName())
                .description(commonNftResponse.getDescription())
                .assetContractAddress(commonNftResponse.getAssetContractAddress())
                .tokenId(commonNftResponse.getTokenId())
                .marketType(commonNftResponse.getMarketType())
                .marketId(commonNftResponse.getMarketId())
                .likeCount(commonNftResponse.getLikeCount())
                .favoriteCount(commonNftResponse.getFavoriteCount())
                .viewCount(commonNftResponse.getViewCount())
                .marketLink(commonNftResponse.getMarketLink())
                .username(commonNftResponse.getUsername())
                .userImageUrl(commonNftResponse.getUserImageUrl())
                .nftImageUrl(commonNftResponse.getNftImageUrl())
                .collectionName(commonNftResponse.getCollectionName())
                .nftCollectionId(commonNftResponse.getNftCollectionId())
                .nftAssetId(commonNftResponse.getNftAssetId())
                .frameNftId(commonNftResponse.getFrameNftId())
                .userUrl(commonNftResponse.getUserUrl())
                .tagType(commonNftResponse.getTagType())
                .createdDate(commonNftResponse.getCreatedDate())
                .asset(nftResponse.getNftAsset())
                .collections(nftResponse.getNftCollection())
                .propList(propertyResponse)
                .build();

        return response;
    }

    /**
     * Nft 정보 Response Builder 함수
     * @param nftInfo
     * @return
     */
    public CommonNftResponse getCommonNftResponse(Nft nftInfo) {
        String userName  = null;
        String userImage = null;
        Long likeCount = Long.valueOf(0);
        Long favoriteCount = Long.valueOf(0);
        Long viewCount = Long.valueOf(0);
        String tagType = "image";
        String userUrl   = "/gallery/"+nftInfo.getNftCollection().getNftCollectionId();    // 기본 콜렉션|회원|비회원용 url

        if(nftInfo.getLastSaleProfileImageUrl() != null) {
            userImage = nftInfo.getLastSaleProfileImageUrl();
        } else if(nftInfo.getOwnerProfileImageUrl() != null) {
            userImage = nftInfo.getOwnerProfileImageUrl();
        } else if(nftInfo.getCreatorProfileImageUrl() != null) {
            userImage = nftInfo.getCreatorProfileImageUrl();
        }
        if(nftInfo.getLastSaleUserName() != null && !nftInfo.getLastSaleUserName().equals("NullAddress")) {
            userName = nftInfo.getLastSaleUserName();
        } else if(nftInfo.getOwnerUserName() != null && !nftInfo.getOwnerUserName().equals("NullAddress")) {
            userName = nftInfo.getOwnerUserName();
        } else if(nftInfo.getCreatorUserName() != null && !nftInfo.getCreatorUserName().equals("NullAddress")) {
            userName = nftInfo.getCreatorUserName();
        }

        if(nftInfo.getLikeCount().toString() != "") {
            likeCount = nftInfo.getLikeCount();
        }
        if(nftInfo.getFavoriteCount().toString() != "") {
            favoriteCount = nftInfo.getFavoriteCount();
        }
        if(nftInfo.getViewCount().toString() != "") {
            viewCount = nftInfo.getViewCount();
        }
        if(userName == null) {
            userName = "#null";
        } else {
            if(nftInfo.getNft_member_id() != null) {
                userUrl = "/user/" + nftInfo.getNft_member_id();
            } else {
                userUrl = "/user/username/" + userName;
            }
        }
        String regExp = ".mp4";
        boolean imageUrl = nftInfo.getImageUrl().contains(regExp);

        if(imageUrl) {
            tagType = "video";
        }
        CommonNftResponse commonNftResponse = CommonNftResponse.builder()
                .nftId(nftInfo.getNftId())
                .name(nftInfo.getName())
                .username(userName)
                .likeCount(likeCount)
                .favoriteCount(favoriteCount)
                .viewCount(viewCount)
                .marketLink(nftInfo.getOpenseaLink())
                .userImageUrl(userImage)
                .nftImageUrl(nftInfo.getImageUrl())
                .nftCollectionName(nftInfo.getCollectionName())
                .nftCollectionId(nftInfo.getNftCollection().getNftCollectionId())
                .assetContractAddress(nftInfo.getAssetContractAddress())
                .tokenId(nftInfo.getTokenId())
                .tagType(tagType)
                .frameNftId(nftInfo.getFrameNftId())
                .userUrl(userUrl)
                .localDate(nftInfo.getCreateDate())
                .description(nftInfo.getDescription())
                .marketType(nftInfo.getMarketType())
                .marketId(nftInfo.getMarketId())
                .collectionName(nftInfo.getCollectionName())
                .nftAssetId(nftInfo.getNftAsset().getNftAssetId())
                .createdDate(nftInfo.getCreateDate())
                .build();

        return commonNftResponse;
    }

    public int lastNum(int num, int size) {
        if(size < num) {
            return size;
        }
        return num;
    }

}
