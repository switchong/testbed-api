package com.nftgram.web.member.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class NftMemberLoginRequest {

    private String id;

    private String password;

}
