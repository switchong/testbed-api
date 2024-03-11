package com.testbed.web.gallery.dto;

import com.testbed.web.common.dto.response.CommonNftResponse;
import com.testbed.web.common.dto.response.NftCommentResponse;
import com.testbed.web.common.dto.response.NftLikeResponse;
import com.testbed.web.common.dto.response.NftMemberResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class GalleryNftOneDto {

    private CommonNftResponse commonNftResponseList;

    private List<NftCommentResponse> commentResponses;

    private List<NftLikeResponse> likeResponses;

    private NftMemberResponse memberResponse;

    private boolean isLike;

    private boolean isMember;

    public GalleryNftOneDto(CommonNftResponse commonNftResponseList, List<NftCommentResponse> commentResponses, List<NftLikeResponse> likeResponses) {
        this.commonNftResponseList = commonNftResponseList;
        this.commentResponses = commentResponses;
        this.likeResponses = likeResponses;
        this.isLike = false;
        this.isMember = false;
        this.memberResponse = null;
    }

    public GalleryNftOneDto(CommonNftResponse commonNftResponseList, List<NftCommentResponse> commentResponses, List<NftLikeResponse> likeResponses,
                               boolean isLike, boolean isMember, NftMemberResponse memberResponse) {
        this.commonNftResponseList = commonNftResponseList;
        this.commentResponses = commentResponses;
        this.likeResponses = likeResponses;
        this.isLike = isLike;
        this.isMember = isMember;
        this.memberResponse = memberResponse;
    }

}
