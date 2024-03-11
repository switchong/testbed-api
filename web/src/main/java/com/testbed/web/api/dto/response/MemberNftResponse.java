package com.testbed.web.api.dto.response;

import com.testbed.core.domain.testbed.NftLike;
import com.testbed.core.domain.testbed.NftMember;
import com.testbed.web.common.dto.response.NftCommentResponse;
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
