package com.testbed.web.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
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
