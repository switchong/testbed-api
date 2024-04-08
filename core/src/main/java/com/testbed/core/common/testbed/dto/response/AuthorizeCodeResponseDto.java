package com.testbed.core.common.testbed.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizeCodeResponseDto {

    @JsonProperty("requestUrl")
    private String requestUrl;

    @JsonProperty("html")
    private String html;

}
