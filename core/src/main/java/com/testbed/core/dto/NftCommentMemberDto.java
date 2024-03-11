package com.testbed.core.dto;

import com.testbed.core.domain.nftgram.NftComment;
import com.testbed.core.domain.nftgram.NftMember;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NftCommentMemberDto {
    private NftComment nftComment;
    private NftMember nftMember;
}
