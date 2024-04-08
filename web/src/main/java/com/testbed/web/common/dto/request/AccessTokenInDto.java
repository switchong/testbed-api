package com.testbed.web.common.dto.request;

import com.testbed.core.domain.common.value.ActiveStatus;
import com.testbed.core.domain.testbed.value.Scope;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccessTokenInDto {
    private String accessToken;
    private String refreshToken;
    private Scope scope;
    private ActiveStatus activeStatus;
    private String userSeqNo;
    private int expiresIn;
    private LocalDateTime expiresDate;
    private LocalDateTime createDate;

    @Builder
    public AccessTokenInDto(String accessToken, String refreshToken, Scope scope, String userSeqNo, int expiresIn) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.scope = scope;
        this.userSeqNo = userSeqNo;
        this.activeStatus = ActiveStatus.ACTIVE;
        this.expiresIn = expiresIn;
    }
}
