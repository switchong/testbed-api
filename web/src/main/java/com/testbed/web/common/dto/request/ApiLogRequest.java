package com.testbed.web.common.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * API_LOG 테이블 컬럼
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiLogRequest {

    private String uri_id;
    private String uri_path;
    private String method;
    private String request;
    private String response;
    private String rsp_code;
    private String rsp_message;

    @Builder
    public ApiLogRequest(String uri_id, String uri_path, String method, String request) {
        this.uri_id = uri_id;
        this.uri_path = uri_path;
        this.method = method;
        this.request = request;
    }

    @Builder
    public ApiLogRequest(String uri_id, String uri_path, String method, String request,
                         String response, String rsp_code, String rsp_message) {
        this.uri_id = uri_id;
        this.uri_path = uri_path;
        this.method = method;
        this.request = request;
        this.response = response;
        this.rsp_code = rsp_code;
        this.rsp_message = rsp_message;
    }
}
