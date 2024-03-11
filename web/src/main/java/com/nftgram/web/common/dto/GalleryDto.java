package com.nftgram.web.common.dto;

import com.testbed.core.domain.nftgram.NftCollection;
import com.nftgram.web.common.dto.response.CommonNftResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GalleryDto {
    private NftCollection collection;
    private List<List<CommonNftResponse>> slideList = new ArrayList<>();
    private List<CommonNftResponse> galleryList = new ArrayList<>();

    @Builder
    public GalleryDto(NftCollection collection, List<CommonNftResponse> galleryList, List<List<CommonNftResponse>> slideList) {
        this.collection = collection;
        this.galleryList = galleryList;
        this.slideList = slideList;
    }
}
