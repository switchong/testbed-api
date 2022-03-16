package com.nftgram.admin.memberlist.dto.response;


import com.nftgram.core.domain.member.MemberStatus;
import com.nftgram.core.domain.nftgram.NftMember;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NftMemberResponse {

    private NftMember nftMember;
    private Long nftMemberId;
    private String nftMemberUserId;
    private String facebook;
    private String discord;
    private String instagram;
    private String twitter;
    private MemberStatus memberStatus;

}
