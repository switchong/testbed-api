package com.testbed.web.auth.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthorizeResponse {

    private String code;
    private String scope;
    private String state;
}
