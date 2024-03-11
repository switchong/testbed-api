package com.testbed.core.common.opensea.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OpenseaErrorResponse {

    @JsonProperty("code")
    private int code = 0;

    @JsonProperty("message")
    private String message;

    @JsonProperty("limit")
    private String limit;
}
