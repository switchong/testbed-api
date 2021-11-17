package com.nftgram.core.domain.dto;


import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NftMemberDto {


    private String nftMemberUserId;
    private String password;

    public NftMemberDto(String nftMemberUserId, String password) {
        this.nftMemberUserId = nftMemberUserId;
        this.password = password;
    }


}
