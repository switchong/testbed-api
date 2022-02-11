package com.nftgram.admin.member.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NftMemberAuthDto {

    private String loginYN;

    private Long nftMemberId;

    private String nftMemberUserId;



    @Builder
    public NftMemberAuthDto(String loginYN, Long nftMemberId, String nftMemberUserId) {
        this.loginYN = loginYN;
        this.nftMemberId = nftMemberId;
        this.nftMemberUserId = nftMemberUserId;
    }
}
