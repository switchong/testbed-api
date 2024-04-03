package com.testbed.web.auth.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Getter
@NoArgsConstructor
public class AccessTokenOobRequest {

    @Value("${testbed.clientId}")
    private String clientId;

    @Value("${testbed.clientSecret}")
    private String clientSecret;

    private String scope;
    private String grantType;

}
