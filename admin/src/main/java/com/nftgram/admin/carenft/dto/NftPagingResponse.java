package com.nftgram.admin.carenft.dto;

import com.nftgram.admin.carenft.dto.response.NftResponse;
import com.nftgram.admin.common.converter.PagingConverter;

import lombok.Getter;

import java.util.List;

@Getter
public class NftPagingResponse {

    private List<NftResponse> nftResponse;
    private PagingConverter pagingConverter;


    public NftPagingResponse(List<NftResponse> nftResponse, PagingConverter pagingConverter) {
        this.nftResponse = nftResponse;
        this.pagingConverter = pagingConverter;
    }
}
