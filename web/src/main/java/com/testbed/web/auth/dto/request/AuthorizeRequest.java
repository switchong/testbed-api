package com.testbed.web.auth.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthorizeRequest {

    private String responseType;

    @Value("${testbed.clientId}")
    private String clientId;

    private String redirectUri; // 리다이렉트 URL
    private String scope;
    private String state;
    private String authType;    // (0:최초인증, 1:재인증, 2:인증생략)

}
