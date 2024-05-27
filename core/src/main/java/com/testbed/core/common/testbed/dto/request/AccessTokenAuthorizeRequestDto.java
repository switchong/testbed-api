package com.testbed.core.common.testbed.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AccessTokenAuthorizeRequestDto {

    private static AccessTokenAuthorizeRequestDto accessTokenAuthorizeRequestDto;

    @JsonProperty("code")
    private String code;

    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("client_secret")
    private String clientSecret;

    @JsonProperty("redirect_uri")
    private String redirectUri;

    @JsonProperty("grant_type")
    private String grantType;

    public AccessTokenAuthorizeRequestDto(String code, String clientId, String clientSecret, String redirectUri, String grantType) {
        this.code = code;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
        this.grantType = grantType;
    }

    public static AccessTokenAuthorizeRequestDto of (String code, String clientId, String clientSecret, String redirectUri, String grantType) {
        return new AccessTokenAuthorizeRequestDto(code, clientId, clientSecret, redirectUri, grantType);
    }
}
