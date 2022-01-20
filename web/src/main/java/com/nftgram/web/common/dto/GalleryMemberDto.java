package com.nftgram.web.common.dto;

import com.nftgram.core.domain.nftgram.NftMember;
import com.nftgram.web.common.dto.response.CommonNftResponse;
import com.nftgram.web.common.dto.response.CommonNftSlider;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GalleryMemberDto {
    private Long sliderCount;
    private NftMember nftMember;
    private List<List<CommonNftResponse>> nftSliderList = new ArrayList<>();
    private List<CommonNftResponse> nftResponseList = new ArrayList<>();


    @Builder
    public GalleryMemberDto(Long sliderCount, NftMember nftMember, List<CommonNftResponse> nftResponseList, List<List<CommonNftResponse>> nftSliderList) {
        this.sliderCount = sliderCount;
        this.nftMember = nftMember;
        this.nftSliderList = nftSliderList;
        this.nftResponseList = nftResponseList;
    }
}
