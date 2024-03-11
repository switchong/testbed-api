package com.testbed.web.api.dto;

import com.testbed.web.common.dto.response.CommonNftResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MainPageDto {
    private int total;
    private List<CommonNftResponse> nftList;
    private List<List<CommonNftResponse>> slideList = new ArrayList<>();

    @Builder
    public MainPageDto(int total, List<CommonNftResponse> nftList, List<List<CommonNftResponse>> slideList ) {
        this.total = total;
        this.nftList = nftList;
        this.slideList = slideList;

    }
}
