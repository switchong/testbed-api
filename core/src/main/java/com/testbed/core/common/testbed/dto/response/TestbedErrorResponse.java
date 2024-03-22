package com.testbed.core.common.testbed.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TestbedErrorResponse {

    @JsonProperty("rspCode")
    private String rspCode;

    @JsonProperty("rspMessage")
    private String rspMessage;

}
