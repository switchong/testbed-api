package com.testbed.web.auth.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthorizeWebResponse {

    private String code;
    private String scope;
    private String state;
    private String userId;
    private String tokenUrl;
    private String tokenScope;
//    private String rspCode;
//    private String rspMessage;
//    private String error;
//    private String errorDescription;

    @Builder
    public AuthorizeWebResponse(String code, String scope, String state, String userId, String tokenUrl, String tokenScope) {
        this.code = code;
        this.scope = scope;
        this.state = state;
        this.userId = userId;
        this.tokenUrl = tokenUrl;
        this.tokenScope = tokenScope;
    }
}
