package com.nftgram.core.domain.dto;


import com.nftgram.core.domain.member.MemberStatus;
import com.nftgram.core.domain.nftgram.NftMember;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public final class NftMemberDto {

    private String nftMemberUserId;
    private String password;


//    public NftMemberDto(String nftMemberUserId, String password) {
//        this.nftMemberUserId = nftMemberUserId;
//        this.password = password;
//
//    }
//
//    public NftMember toEntity(){
//        return NftMember.builder()
//                .nftMemberUserId(nftMemberUserId)
//                .password(password)
//                .memberStatus(MemberStatus.ACTIVE)
//                .build();
//    }


}
