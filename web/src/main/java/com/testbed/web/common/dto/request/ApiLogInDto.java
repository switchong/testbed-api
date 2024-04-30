package com.testbed.web.common.dto.request;

import com.testbed.core.domain.testbed.value.Method;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * API_LOG 테이블 컬럼
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiLogInDto {

    private String uriId;
    private String uriPath;
    private Method method;
    private String state;
    private String request;
    private String response;
    private String rspCode;
    private String rspMessage;

    @Builder
    public ApiLogInDto(String uriId, String uriPath, Method method, String state, String request, String response
                        ,String rspCode, String rspMessage) {
        this.uriId = uriId;
        this.uriPath = uriPath;
        this.method = method;
        this.state = state;
        this.request = request;
        this.response = response;
        this.rspCode = rspCode;
        this.rspMessage = rspMessage;
    }
}
