package com.testbed.web.common.dto.response;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
public class CommonNftSlider {
    private List<CommonNftResponse> nftResponseList = new ArrayList<>();

    @Builder
    public CommonNftSlider(List<CommonNftResponse> nftResponseList) {
        this.nftResponseList = nftResponseList;
    }
}
