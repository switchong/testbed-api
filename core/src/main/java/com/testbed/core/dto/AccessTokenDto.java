package com.testbed.core.dto;

import com.testbed.core.domain.testbed.value.Scope;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccessTokenDto {

    private String userId;
    private Scope scope;
    private String accessToken;
    private LocalDateTime expiresDate;

    @Builder
    public AccessTokenDto(String userId, Scope scope, String accessToken, LocalDateTime expiresDate) {
        this.userId = userId;
        this.scope = scope;
        this.accessToken = accessToken;
        this.expiresDate = expiresDate;

    }

}
