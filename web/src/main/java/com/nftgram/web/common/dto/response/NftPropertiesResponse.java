package com.nftgram.web.common.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NftPropertiesResponse {

    private Long propId;

    private Long nftId;

    private String traitType;

    private String traitValue;

    private Long orderCount;

    @Builder
    public NftPropertiesResponse(Long propId, Long nftId, String traitType, String traitValue, Long orderCount) {
        this.propId = propId;
        this.nftId = nftId;
        this.traitType = traitType;
        this.traitValue = traitValue;
        this.orderCount = orderCount;
    }

}
