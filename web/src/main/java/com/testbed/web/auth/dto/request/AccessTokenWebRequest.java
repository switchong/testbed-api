package com.testbed.web.auth.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class AccessTokenWebRequest {

    private String userId;
    private String scope;   // AUTHORIZE , OOB
    private String code;    // authoruzation_code
}
