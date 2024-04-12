package com.testbed.web.auth.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthorizeResponse {

    private String code;
    private String scope;
    private String state;
    private String clientInfo;
//    private String rspCode;
//    private String rspMessage;
//    private String error;
//    private String errorDescription;

    @Builder
    public AuthorizeResponse(String code, String scope, String state, String clientInfo) {
        this.code = code;
        this.scope = scope;
        this.state = state;
        this.clientInfo = clientInfo;
    }
}
