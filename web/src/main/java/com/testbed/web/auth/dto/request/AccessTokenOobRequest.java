package com.testbed.web.auth.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@AllArgsConstructor
@Setter
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
