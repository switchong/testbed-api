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
public class AccessTokenOobRequestDto {

    private static AccessTokenOobRequestDto accessTokenOobRequestDto;

    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("client_secret")
    private String clientSecret;

    @JsonProperty("scope")
    private String scope;

    @JsonProperty("grant_type")
    private String grantType;



    public AccessTokenOobRequestDto(String clientId, String clientSecret, String scope, String grantType) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.scope = scope;
        this.grantType = grantType;
    }

    public static AccessTokenOobRequestDto of (String clientId, String clientSecret, String scope, String grantType) {
        return new AccessTokenOobRequestDto(clientId, clientSecret, scope, grantType);
    }
}
