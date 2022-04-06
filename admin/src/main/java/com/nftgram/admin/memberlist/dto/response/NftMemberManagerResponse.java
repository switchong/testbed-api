package com.nftgram.admin.memberlist.dto.response;

import com.nftgram.admin.common.converter.PagingConverter;
import lombok.Getter;

import java.util.List;

@Getter
public class NftMemberManagerResponse {

    private List<NftMemberResponse> nftMemberResponse;
    private PagingConverter pagingConverter;

    public NftMemberManagerResponse(List<NftMemberResponse> nftMemberResponse, PagingConverter pagingConverter) {
        this.nftMemberResponse = nftMemberResponse;
        this.pagingConverter = pagingConverter;
    }
}
