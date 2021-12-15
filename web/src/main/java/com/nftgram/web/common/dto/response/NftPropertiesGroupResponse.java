package com.nftgram.web.common.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NftPropertiesGroupResponse {
    private String traitType;

    private String traitValue;

    private Long traitCount;

    private Short traitPercent;

    @Builder
    public NftPropertiesGroupResponse(String traitType, String traitValue, Long traitCount, Short traitPercent) {
        this.traitType = traitType;
        this.traitValue = traitValue;
        this.traitCount = traitCount;
        this.traitPercent = traitPercent;
    }
}
