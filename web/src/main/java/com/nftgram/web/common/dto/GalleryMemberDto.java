package com.nftgram.web.common.dto;

import com.nftgram.core.domain.nftgram.NftMember;
import com.nftgram.web.common.dto.response.CommonNftResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GalleryMemberDto {
    private NftMember nftMember;
    private List<CommonNftResponse> nftResponseList = new ArrayList<>();

    @Builder
    public GalleryMemberDto(NftMember nftMember, List<CommonNftResponse> nftResponseList) {
        this.nftMember = nftMember;
        this.nftResponseList = nftResponseList;
    }
}
