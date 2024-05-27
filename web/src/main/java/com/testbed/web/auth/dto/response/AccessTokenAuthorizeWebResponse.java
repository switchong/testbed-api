package com.testbed.web.auth.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccessTokenAuthorizeWebResponse {

    private String accessToken;
    private String tokenType;
    private String expiresIn;
    private String refreshToken;
    private String scope;
    private String userSeqNo;

}
