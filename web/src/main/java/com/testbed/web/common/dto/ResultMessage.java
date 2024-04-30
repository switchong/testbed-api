package com.testbed.web.common.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResultMessage {

    private String code;
    private String message;

    @Builder
    public ResultMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
