package com.nftgram.admin.memberlist.dto.response;


import com.nftgram.core.domain.member.MemberStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NftMemberResponse {

    private Long nftMemberId;
    private String nftMemberUserId;
    private String facebook;
    private String discord;
    private String instagram;
    private String twitter;
    private LocalDateTime localDateTime;
    private MemberStatus memberStatus;


}
