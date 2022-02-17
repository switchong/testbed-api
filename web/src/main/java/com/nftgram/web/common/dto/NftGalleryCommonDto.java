package com.nftgram.web.common.dto;

import com.nftgram.core.domain.nftgram.NftCollection;
import com.nftgram.core.domain.nftgram.NftMember;
import com.nftgram.core.dto.NftCommonDto;
import com.nftgram.web.common.dto.response.CommonNftResponse;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NftGalleryCommonDto {
    private boolean sortFlag;
    private int total;
    private NftCommonDto commonDto;
    private Long sliderCount;
    private NftMember member;
    private NftCollection collection;
    private List<CommonNftResponse> nftResponseList = new ArrayList<>();
    private List<List<CommonNftResponse>> nftSliderList = new ArrayList<>();

    @Builder
    public NftGalleryCommonDto(boolean sortFlag, int total, Long sliderCount, NftMember member, NftCollection collection, NftCommonDto commonDto,
                               List<CommonNftResponse> nftResponseList, List<List<CommonNftResponse>> nftSliderList) {
        this.sortFlag = sortFlag;
        this.total = total;
        this.commonDto = commonDto;
        this.sliderCount = sliderCount;
        this.member = member;
        this.collection = collection;
        this.nftSliderList = nftSliderList;
        this.nftResponseList = nftResponseList;

    }
}
