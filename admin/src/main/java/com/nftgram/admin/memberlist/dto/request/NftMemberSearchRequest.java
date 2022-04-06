package com.nftgram.admin.memberlist.dto.request;


import com.nftgram.admin.common.util.PageRequest;
import com.nftgram.core.domain.member.MemberStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NftMemberSearchRequest extends PageRequest {


    private Long nftMemberId;
    private String facebook;
    private String discord;
    private String instagram;
    private String twitter;
    private String username;
    private String nftMemberUserId;
    private MemberStatus memberStatus;
}
