package com.nftgram.web.api.service;

import com.nftgram.core.domain.common.value.ActiveStatus;
import com.nftgram.core.domain.nftgram.NftLike;
import com.nftgram.core.domain.nftgram.NftMember;
import com.nftgram.core.domain.nftgram.NftMemberWallet;
import com.nftgram.core.dto.NftOneJoinDto;
import com.nftgram.core.dto.NftPropGroupDto;
import com.nftgram.core.repository.*;
import com.nftgram.web.api.dto.response.MemberWalletResponses;
import com.nftgram.web.api.dto.response.UpdateLikeCountResponse;
import com.nftgram.web.common.dto.NftPropertiesGroupDto;
import com.nftgram.web.common.dto.response.CommonNftResponse;
import com.nftgram.web.common.dto.response.NftPropertiesGroupResponse;
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
    private final NftMemberWalletRepository nftMemberWalletRepository;
    private final NftLikeRepository nftLikeRepository;
    private final NftPropertyRepository nftPropertyRepository;
    private Object typeArr;

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

    @Transactional(readOnly = true)
    public NftMember memberInfo(Long memberId ) {

        NftMember nftMemberResponse = nftMemberRepository.findByNftMemberId(memberId);
        //Long nftMemberSnsUrlUpdate = nftMemberRepository.updateNftMember(memberId );


        return nftMemberResponse;
    }

    @Transactional(readOnly = true)
    public List<MemberWalletResponses> memberWalletResponses(Long memberId, String loginFlag) {
        List<NftMemberWallet> nftMemberWallets = nftMemberWalletRepository.walletByMemberId(memberId);

        List<MemberWalletResponses> responses = new ArrayList<>();

        if(nftMemberWallets != null) {
            nftMemberWallets.forEach(wallets -> {
                if(wallets.getActiveStatus().equals(ACTIVE)) {
                    MemberWalletResponses memberWalletResponse = MemberWalletResponses.builder()
                            .wId(wallets.getNftMemberWalletId())
                            .wContractAddress(wallets.getWalletContractAddress())
                            .wType(wallets.getWalletType())
                            .createdDate(wallets.getCreateDate())
                            .build();
                    responses.add(memberWalletResponse);
                }
            });
        }

        return responses;
    }

    @Transactional(rollbackFor = Exception.class)
    public Long memberWalletSave(String walletContractAddress, Long memberId) {
        Long isResult = Long.valueOf(1);
        // 지갑주소 중복 체크

        NftMember nftMember = nftMemberRepository.findByNftMemberId(memberId);
        NftMemberWallet chkNftMemberWallet = nftMemberWalletRepository.walletByContractAddress(walletContractAddress);

        // contractAddress 중복체크
        if(chkNftMemberWallet != null) {
            if(chkNftMemberWallet.getActiveStatus() == ACTIVE){
                if(chkNftMemberWallet.getNftMember().getNftMemberId().equals(memberId)) {
                    isResult = Long.valueOf(3);
                } else {
                    isResult = Long.valueOf(4);
                }
            } else {
                if(chkNftMemberWallet.getNftMember().getNftMemberId().equals(memberId)) {
                    isResult = Long.valueOf(5);
                    nftMemberWalletRepository.updateWalletStatus(chkNftMemberWallet.getNftMemberWalletId(),memberId, ACTIVE);
                } else {
                    isResult = Long.valueOf(6);
                }
            }
        }

        if(isResult.equals(Long.valueOf(1))) {
            // NftMemberWallet Save
            NftMemberWallet nftMemberWallet = NftMemberWallet.builder()
                    .nftMember(nftMember)
                    .walletContractAddress(walletContractAddress)
                    .build();

            nftMemberWalletRepository.save(nftMemberWallet);
        }

        return isResult;
    }

    @Transactional(rollbackFor = Exception.class)
    public Long memberWalletDeleteStatus(Long walletId, Long memberId) {
        Long result = nftMemberWalletRepository.deleteWalletStatus(walletId, memberId);

        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    public Long memberWalletByNftMatching() {
        Long isResult = Long.valueOf(1);
        return isResult;

    }
}
