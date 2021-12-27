package com.nftgram.web.api.dto;

import com.nftgram.web.common.dto.response.CommonNftResponse;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MainPageDto {
    private int total;
    private List<CommonNftResponse> nftList;





    @Builder
    public MainPageDto(int total, List<CommonNftResponse> nftList) {
        this.total = total;
        this.nftList = nftList;

    }
}
