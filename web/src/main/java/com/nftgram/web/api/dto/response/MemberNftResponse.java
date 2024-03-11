package com.nftgram.web.api.dto.response;

import com.testbed.core.domain.nftgram.NftLike;
import com.testbed.core.domain.nftgram.NftMember;
import com.nftgram.web.common.dto.response.NftCommentResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberNftResponse {
    private Long memberId;

    private String memberUserId;

    private String displayStyle;

    private NftMember nftMember;

    private List<NftLike> nftLikeList = new ArrayList<>();

    private List<NftCommentResponse> nftCommentResponses = new ArrayList<>();



}
