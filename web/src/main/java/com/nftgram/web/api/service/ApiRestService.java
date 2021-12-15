package com.nftgram.web.api.service;

import com.nftgram.core.domain.common.value.ActiveStatus;
import com.nftgram.core.domain.nftgram.NftLike;
import com.nftgram.core.domain.nftgram.NftMember;
import com.nftgram.core.dto.NftOneJoinDto;
import com.nftgram.core.dto.NftPropGroupDto;
import com.nftgram.core.repository.NftLikeRepository;
import com.nftgram.core.repository.NftMemberRepository;
import com.nftgram.core.repository.NftPropertyRepository;
import com.nftgram.core.repository.NftRepository;
import com.nftgram.web.api.dto.response.GetNftOneResponse;
import com.nftgram.web.api.dto.response.UpdateLikeCountResponse;
import com.nftgram.web.common.dto.NftPropertiesGroupDto;
import com.nftgram.web.common.dto.response.CommonNftResponse;
import com.nftgram.web.common.dto.response.NftPropertiesGroupResponse;
import com.nftgram.web.common.dto.response.NftPropertyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.nftgram.core.domain.common.value.ActiveStatus.ACTIVE;
import static com.nftgram.core.domain.common.value.ActiveStatus.DELETE;

@RequiredArgsConstructor
@Service
@Transactional
public class ApiRestService {

    private CommonNftResponse commonNftResponse;
    private final NftRepository nftRepository;
    private final NftMemberRepository nftMemberRepository;
    private final NftLikeRepository nftLikeRepository;
    private final NftPropertyRepository nftPropertyRepository;
    private Object typeArr;

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
        String userName = null;
        String userImage = null;
        Long likeCount = Long.valueOf(0);
        Long favoriteCount = Long.valueOf(0);
        Long viewCount = Long.valueOf(0);

        if(nftResponse.getNft().getLastSaleProfileImageUrl() != null) {
            userImage = nftResponse.getNft().getLastSaleProfileImageUrl();
        } else if(nftResponse.getNft().getOwnerProfileImageUrl() != null) {
            userImage = nftResponse.getNft().getOwnerProfileImageUrl();
        } else if(nftResponse.getNft().getCreatorProfileImageUrl() != null) {
            userImage = nftResponse.getNft().getCreatorProfileImageUrl();
        }
        if(nftResponse.getNft().getLastSaleUserName() != null && !nftResponse.getNft().getLastSaleUserName().equals("NullAddress")) {
            userName = nftResponse.getNft().getLastSaleUserName();
        } else if(nftResponse.getNft().getOwnerUserName() != null && !nftResponse.getNft().getOwnerUserName().equals("NullAddress")) {
            userName = nftResponse.getNft().getOwnerUserName();
        } else if(nftResponse.getNft().getCreatorUserName() != null && !nftResponse.getNft().getCreatorUserName().equals("NullAddress")) {
            userName = nftResponse.getNft().getCreatorUserName();
        }
        if(userName == null) {
            userName = "#null";
        }
        if(nftResponse.getNft().getLikeCount().toString() != "") {
            likeCount = nftResponse.getNft().getLikeCount();
        }
        if(nftResponse.getNft().getFavoriteCount().toString() != "") {
            favoriteCount = nftResponse.getNft().getFavoriteCount();
        }
        if(nftResponse.getNft().getViewCount().toString() != "") {
            viewCount = nftResponse.getNft().getViewCount();
        }
        
        GetNftOneResponse response = GetNftOneResponse.builder()
                .likeFlag(likeFlag)
                .nftId(nftResponse.getNft().getNftId())
                .name(nftResponse.getNft().getName())
                .description(nftResponse.getNft().getDescription())
                .assetContractAddress(nftResponse.getNft().getAssetContractAddress())
                .tokenId(nftResponse.getNft().getTokenId())
                .marketType(nftResponse.getNft().getMarketType())
                .marketId(nftResponse.getNft().getMarketId())
                .likeCount(likeCount)
                .favoriteCount(favoriteCount)
                .viewCount(viewCount)
                .marketLink(nftResponse.getNft().getOpenseaLink())
                .username(userName)
                .userImageUrl(userImage)
                .nftImageUrl(nftResponse.getNft().getImageUrl())
                .collectionName(nftResponse.getNft().getCollectionName())
                .nftCollectionId(nftResponse.getNft().getNftCollection().getNftCollectionId())
                .nftAssetId(nftResponse.getNft().getNftAsset().getNftAssetId())
                .borderImageId(nftResponse.getNft().getBorderImageId())
                .createdDate(nftResponse.getNft().getCreateDate())
                .asset(nftResponse.getNftAsset())
                .collections(nftResponse.getNftCollection())
                .propList(propertyResponse)
                .build();

        return response;
    }

    @Transactional(readOnly = true)
    public NftPropertiesGroupDto nftPropertiesCountDto() {
        // Properties Group TraitType
        List<NftPropGroupDto> traitTypeList = nftPropertyRepository.findByTraitTypeGroupCount();
        List<NftPropertiesGroupResponse> traitTypeGroup = new ArrayList<>();
        List<NftPropertiesGroupResponse> traitTypeValueGroup = new ArrayList<>();
        traitTypeList.forEach(trait ->{
            NftPropertiesGroupResponse response = NftPropertiesGroupResponse.builder()
                    .traitType(trait.getTraitType())
                    .traitValue(trait.getTraitValue())
                    .traitCount(trait.getTraitCount())
                    .traitPercent((short) 0)
                    .build();
            traitTypeGroup.add(response);
        });
        // Properties Group TraitType, TraitValue
        List<NftPropGroupDto> traitTypeValueList = nftPropertyRepository.findByTraitTypeValueGroupCount();
        traitTypeValueList.forEach(trait ->{
            NftPropertiesGroupResponse response = NftPropertiesGroupResponse.builder()
                    .traitType(trait.getTraitType())
                    .traitValue(trait.getTraitValue())
                    .traitCount(trait.getTraitCount())
                    .build();
            traitTypeValueGroup.add(response);
        });
        NftPropertiesGroupDto nftPropertiesGroupDto = NftPropertiesGroupDto.builder()
                .propTypeGroup(traitTypeGroup)
                .propTypeValueGroup(traitTypeValueGroup)
                .build();

        return nftPropertiesGroupDto;
    }

    @Transactional(rollbackFor = Exception.class)
    public Long updateViewCount(Long nftId) {
        Long result = nftRepository.updateNftViewCount(nftId);

        Long viewTotal = nftRepository.countNftViewCount(nftId);

        return viewTotal;
    }

    @Transactional(rollbackFor = Exception.class)
    public UpdateLikeCountResponse updateLikeCount(Long nftId, Long nftMemberId) {
        // NFT 정보 조회
        NftOneJoinDto nftResponse = nftRepository.findByNftIdOne(nftId);
        // MEMBER 정보 조회
        NftMember memberInfo = nftMemberRepository.findByNftMemberId(nftMemberId);
        // LIKE 확인
        NftLike nftLikeRepositories = nftLikeRepository.findByLikeMemberIdOne(nftId, nftMemberId);

        String likeFlag = "Y";
        if(nftLikeRepositories != null) {

            ActiveStatus activeStatus = ACTIVE;
            if(nftLikeRepositories.getActiveStatus() == ACTIVE) {
                activeStatus = DELETE;
                likeFlag = "N";
                nftRepository.updateNftLikeCountMinus(nftId);
            } else {
                nftRepository.updateNftLikeCountPuls(nftId);
            }
            nftLikeRepository.deleteByLikeMember(nftLikeRepositories.getLikeId(), activeStatus);

        } else {
            NftLike nftBuild = NftLike.builder()
                    .nft(nftResponse.getNft())
                    .nftMember(memberInfo)
                    .assetContractAddress(nftResponse.getNft().getAssetContractAddress())
                    .tokenId(nftResponse.getNft().getTokenId())
                    .build();
            NftLike insert = nftLikeRepository.save(nftBuild);
            Long likeId = insert.getLikeId();
            nftRepository.updateNftLikeCountPuls(nftId);
        }
        Long likeTotal = nftRepository.countNftLikeCount(nftId);

        UpdateLikeCountResponse updateLikeCountResponse = UpdateLikeCountResponse.builder()
                .loginFlag("Y")
                .likeFlag(likeFlag)
                .nftId(nftId)
                .nftMemberId(nftMemberId)
                .likeTotalCount(likeTotal)
                .build();

        return updateLikeCountResponse;

    }
}
