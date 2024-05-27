package com.testbed.web.auth.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccessTokenOobWebResponse {

    private String accessToken;
    private String tokenType;
    private String expiresIn;
    private String scope;
    private String clientUseCode;
}
