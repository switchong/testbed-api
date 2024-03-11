package com.testbed.web.common.dto;

import com.testbed.core.domain.testbed.NftMember;
import com.testbed.web.common.dto.response.CommonNftResponse;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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