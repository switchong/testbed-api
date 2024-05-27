package com.testbed.web.common.dto.request;

import com.testbed.core.domain.common.value.ActiveStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthorizeCodeCommonDto {

    private String userId;
    private String state;
    private String scope;
    private String authorizationCode;
    private ActiveStatus activeStatus;
    private Long expiresIn;
    private LocalDateTime expiresDate;
    private LocalDateTime createDate;

    @Builder
    public AuthorizeCodeCommonDto(String userId, String state, String scope, String authorizationCode, Long expiresIn) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiresDate = null;

        if(expiresIn.intValue() > 0) {
            expiresDate = now.minusSeconds(expiresIn);
        }
        this.userId = userId;
        this.state = state;
        this.scope = scope;
        this.authorizationCode = authorizationCode;
        this.activeStatus = ActiveStatus.ACTIVE;
        this.expiresIn = expiresIn;
        this.expiresDate = expiresDate;
        this.createDate = now;
    }

}
