package com.nftgram.web.common.service;

import com.nftgram.core.domain.nftgram.Nft;
import com.nftgram.core.domain.nftgram.NftCollection;
import com.nftgram.core.repository.NftAssetRepository;
import com.nftgram.core.repository.NftCollectionRepository;
import com.nftgram.core.repository.NftRepository;
import com.nftgram.web.common.dto.GalleryDto;
import com.nftgram.web.common.dto.response.CommonNftResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class NftFindService {
    private CommonNftResponse commonNftResponse;
    private final NftRepository nftRepository;
    private final NftAssetRepository nftAssetRepository;
    private final NftCollectionRepository nftCollectionRepository;

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
    public GalleryDto findByNftCollectionId(Long collectionId) {
        NftCollection collection = nftCollectionRepository.findNftCollection(collectionId);
        List<Nft> galleryList = nftRepository.findByNftCollectionId(collectionId);

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
     * NFT repository 데이터 공통 파싱
     * @param nftList
     * @return
     */
    public List<CommonNftResponse> setCommonNftResponses(List<Nft> nftList) {
        List<CommonNftResponse> response = new ArrayList<>();

        nftList.forEach(nftInfo -> {
            String userName = null;
            String userImage = null;
            String tagType = "image";
            Long likeCount = Long.valueOf(0);
            Long favoriteCount = Long.valueOf(0);
            Long viewCount = Long.valueOf(0);

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
            if(userName == null) {
                userName = "#null";
//                userName = nftInfo.getCollectionName();
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
            String regExp = ".mp4";
            boolean imageUrl = nftInfo.getImageUrl().contains(regExp);

            if(imageUrl) {
                tagType = "video";
            }
            commonNftResponse = CommonNftResponse.builder()
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
                    .localDate(nftInfo.getCreateDate())
                    .build();
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

    public int lastNum(int num, int size) {
        if(size < num) {
            return size;
        }
        return num;
    }

}
