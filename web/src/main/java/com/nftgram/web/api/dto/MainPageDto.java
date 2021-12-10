package com.nftgram.web.api.dto;

import com.nftgram.web.common.dto.response.CommonNftResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class MainPageDto {
    private int total;
    private List<CommonNftResponse> nftList;

    public MainPageDto(List<CommonNftResponse> nftList, int total) {
        this.nftList = nftList;
        this.total = total;
    }
}
