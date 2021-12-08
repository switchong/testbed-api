package com.nftgram.web.api.dto;

import com.nftgram.web.main.dto.MainResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class MainPageDto {
    private int total;
    private List<MainResponse> mainResponses;

    public MainPageDto(List<MainResponse> mainResponses, int total) {
        this.mainResponses = mainResponses;
        this.total = total;
    }
}
