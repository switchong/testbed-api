package com.testbed.web.auth.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class AuthorizeRequest {

    private String userId;
//
//    private String responseType;
//
//    @Value("${testbed.clientId}")
//    private String clientId;
//
//    private String redirectUri; // 리다이렉트 URL
//    private String scope;
//    private String state;
//    private String authType;    // (0:최초인증, 1:재인증, 2:인증생략)

}
