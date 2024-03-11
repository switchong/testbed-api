package com.testbed.web.common.dto;

import com.testbed.web.common.dto.response.NftCommentResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NftCommentDto {
    private Long nftId;
    private int total;
    private List<NftCommentResponse> commentResponseList;

    @Builder
    public NftCommentDto(Long nftId, int total, List<NftCommentResponse> commentResponseList){
        this.nftId = nftId;
        this.total = total;
        this.commentResponseList = commentResponseList;
    }
}
