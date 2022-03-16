package com.nftgram.admin.memberlist.dto.response;

import com.nftgram.admin.common.converter.PagingConverter;
import lombok.Getter;

import java.util.List;

@Getter
public class NftMemberManagerResponse {

    private List<NftMemberResponse> responseList;
    private PagingConverter pagingConverter;

    public NftMemberManagerResponse(List<NftMemberResponse> responseList, PagingConverter pagingConverter) {
        this.responseList = responseList;
        this.pagingConverter = pagingConverter;
    }
}
