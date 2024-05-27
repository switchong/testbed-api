package com.testbed.web.common.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResultMessageCommonDto {

    private String code;
    private String message;
    private String errorMessage;

    @Builder
    public ResultMessageCommonDto(String code, String message, String errorMessage) {
        this.code = code;
        this.message = message;
        this.errorMessage = errorMessage;
    }
}
