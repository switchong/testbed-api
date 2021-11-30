package com.nftgram.web.member.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Setter
public class NftMemberSignupRequest {

    @NotNull
    private String id;

    @NotNull
    private String firstPassword;

    @NotNull
    private String secondPassword;
}
