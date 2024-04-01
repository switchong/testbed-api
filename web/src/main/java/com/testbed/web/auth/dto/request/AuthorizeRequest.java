package com.testbed.web.auth.dto.request;

import com.testbed.core.common.util.string.RandomStringUtils;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthorizeRequest {

    @Value("${testbed.clientId}")
    private String clientId;

    @Value("${testbed.clientSecret}")
    private String clientSecret;

    private String scope;
    private String state;

    @Builder
    public AuthorizeRequest(String scope) {
        this.scope = scope;
        this.state = RandomStringUtils.randomMix(32);
    }

}
