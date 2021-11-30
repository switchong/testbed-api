package com.nftgram.web.member.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NftMemberAuthDto {

    private Long nftMemberId;

    private String nftMemberUserId;

    @Builder
    public NftMemberAuthDto(Long nftMemberId, String nftMemberUserId) {
        this.nftMemberId = nftMemberId;
        this.nftMemberUserId = nftMemberUserId;
    }
}
