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
public class AuthorizeCodeRequestDto {

    private static AuthorizeCodeRequestDto authorizeCodeRequestDto;
    /*
    'response_type' => 'code',
    'client_id' => $client_id,
    'redirect_uri' => $redirect_url,
    'scope' => 'login inquiry transfer',
    'state' => $state,
    'auth_type' => '0'
     */

    @JsonProperty("responseType")
    private String responseType;

    @JsonProperty("clientId")
    private String clientId;

    @JsonProperty("redirectUri")
    private String redirectUri;

    @JsonProperty("scope")
    private String scope;

    @JsonProperty("state")
    private String state;

    @JsonProperty("authType")
    private String authType;



    public AuthorizeCodeRequestDto(String responseType, String clientId, String redirectUri, String scope, String state, String authType) {
        this.responseType = responseType;
        this.clientId = clientId;
        this.redirectUri = redirectUri;
        this.scope = scope;
        this.state = state;
        this.authType = authType;
    }

    public static AuthorizeCodeRequestDto of (String responseType, String clientId, String redirectUri, String scope, String state, String authType) {
        return new AuthorizeCodeRequestDto(responseType, clientId, redirectUri, scope, state, authType);
    }
}
