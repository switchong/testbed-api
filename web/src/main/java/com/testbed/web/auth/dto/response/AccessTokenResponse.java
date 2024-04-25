package com.testbed.web.auth.dto.response;

import com.testbed.core.domain.testbed.value.Scope;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class AccessTokenResponse {

    private String userId;
    private Scope scope;
    private String accessToken;
    private LocalDateTime expiresDate;
    private String rspCode;
    private String rspMessage;

    @Builder
    public AccessTokenResponse(String userId, Scope scope, String accessToken, LocalDateTime expiresDate, String rspCode, String rspMessage) {
        this.userId = userId;
        this.scope = scope;
        this.accessToken = accessToken;
        this.expiresDate = expiresDate;
        this.rspCode = rspCode;
        this.rspMessage = rspMessage;
    }
}

