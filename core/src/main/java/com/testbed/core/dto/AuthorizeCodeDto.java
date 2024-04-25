package com.testbed.core.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthorizeCodeDto {

    private String state;
    private String authorizationCode;

    @Builder
    public AuthorizeCodeDto (String state, String authorizationCode) {
        this.state = state;
        this.authorizationCode = authorizationCode;
    }
}
