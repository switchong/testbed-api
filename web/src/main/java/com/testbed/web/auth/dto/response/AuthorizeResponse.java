package com.testbed.web.auth.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthorizeResponse {

    private String code;
    private String scope;
    private String state;
//    private String rspCode;
//    private String rspMessage;
//    private String error;
//    private String errorDescription;

    @Builder
    public AuthorizeResponse(String code, String scope, String state) {
        this.code = code;
        this.scope = scope;
        this.state = state;
    }
}
