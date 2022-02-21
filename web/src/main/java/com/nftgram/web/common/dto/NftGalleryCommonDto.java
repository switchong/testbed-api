package com.nftgram.web.common.dto;

import com.nftgram.core.domain.nftgram.NftCollection;
import com.nftgram.core.domain.nftgram.NftMember;
import com.nftgram.web.common.dto.response.CommonNftResponse;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NftGalleryCommonDto {
    private boolean sortFlag;
    private Long total;
    private Long nftListCount;
    private Long sliderCount;
    private NftMember member;
    private NftCollection collection;
    private List<CommonNftResponse> nftList = new ArrayList<>();
    private List<List<CommonNftResponse>> nftSliderList = new ArrayList<>();

    @Builder
    public NftGalleryCommonDto(boolean sortFlag, Long total, Long nftListCount, Long sliderCount, NftMember member, NftCollection collection,
                               List<CommonNftResponse> nftList, List<List<CommonNftResponse>> nftSliderList) {
        this.sortFlag = sortFlag;
        this.total = total;
        this.sliderCount = sliderCount;
        this.nftListCount = nftListCount;
        this.member = member;
        this.collection = collection;
        this.nftList = nftList;
        this.nftSliderList = nftSliderList;

    }
}
