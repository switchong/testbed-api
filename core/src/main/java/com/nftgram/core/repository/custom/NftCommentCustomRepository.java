package com.nftgram.core.repository.custom;

import com.nftgram.core.domain.nftgram.NftComment;
import com.nftgram.core.dto.NftCommentMemberDto;

import java.util.List;

public interface NftCommentCustomRepository {
    List<NftCommentMemberDto> getCommentList(Long nftId, Long page, Long size);

    Long updateCommentStatus(NftComment commentTable);

    Long deleteCommentStatus(Long commId);
}
