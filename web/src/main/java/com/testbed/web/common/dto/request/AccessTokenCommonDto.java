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
public class AccessTokenCommonDto {
    private String userId;
    private String accessToken;
    private String refreshToken;
    private String state;
    private Scope scope;
    private ActiveStatus activeStatus;
    private Long userSeqNo;
    private Long expiresIn;
    private LocalDateTime expiresDate;
    private LocalDateTime createDate;

    @Builder
    public AccessTokenCommonDto(String userId, String accessToken, String refreshToken, String state, Scope scope, Long userSeqNo, Long expiresIn) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiresDate = null;
        if(expiresIn.intValue() > 0) {
            expiresDate = now.minusSeconds(expiresIn);
        }

        this.userId = userId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.state = state;
        this.scope = scope;
        this.userSeqNo = userSeqNo;
        this.activeStatus = ActiveStatus.ACTIVE;
        this.expiresIn = expiresIn;
        this.expiresDate = expiresDate;
        this.createDate = now;
    }

}
