package com.testbed.core.repository.custom;

import com.testbed.core.domain.nftgram.NftComment;
import com.testbed.core.dto.NftCommentMemberDto;

import java.util.List;

public interface NftCommentCustomRepository {
    List<NftCommentMemberDto> getCommentList(Long nftId, Long page, Long size);

    Long updateCommentStatus(NftComment commentTable);

    Long deleteCommentStatus(Long commId);
}
