package com.testbed.web.common.dto.request;

import com.testbed.core.domain.common.value.ActiveStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthorizeCodeInDto {

    private String userId;
    private String state;
    private String scope;
    private String authorizationCode;
    private ActiveStatus activeStatus;
    private int expiresIn;
    private LocalDateTime expiresDate;
    private LocalDateTime createDate;

    @Builder
    public AuthorizeCodeInDto(String userId, String state, String scope, String authorizationCode) {
        this.userId = userId;
        this.state = state;
        this.scope = scope;
        this.authorizationCode = authorizationCode;
        this.activeStatus = ActiveStatus.ACTIVE;
        this.expiresIn = 100000;
    }

}
