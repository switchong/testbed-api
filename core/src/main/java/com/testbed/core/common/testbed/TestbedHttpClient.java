package com.testbed.core.common.testbed;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testbed.core.common.testbed.dto.request.AccessTokenOobRequestDto;
import com.testbed.core.common.testbed.dto.request.AuthorizeCodeRequestDto;
import com.testbed.core.common.testbed.dto.response.AccessTokenResponseDto;
import com.testbed.core.common.testbed.dto.response.AuthorizeCodeResponseDto;
import com.testbed.core.common.util.string.RandomStringUtils;
import com.testbed.core.config.testbed.property.TestbedProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RequiredArgsConstructor
@Component
public class TestbedHttpClient {

    private final RestTemplate testbedRestTemplate;
    private final TestbedProperty testbedProperty;

    /**
     * 사용자 인증 URL
     * @param userId
     * @return
     */
    public String getAuthorizeUrl(String userId, String state, String uriPath) {

        String api_uri = testbedProperty.getApiUri();
        String client_id = testbedProperty.getClientId();
        String callUri = testbedProperty.getLocalUrl();
        String callUriPath = "/auth/authResult";

        String url = api_uri + uriPath; // "/oauth/2.0/authorize"
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("response_type", "code")
                .queryParam("client_id", client_id)
                .queryParam("redirect_uri", callUri + callUriPath)
                .queryParam("scope", "login inquiry transfer")
                .queryParam("state", state)
                .queryParam("client_info", userId)
                .queryParam("auth_type", 0);

        String authUrl = uriBuilder.toUriString();

        System.out.println("url.uriBuilder : " + uriBuilder.toUriString());

        return authUrl;
    }

    /**
     * Authorize 사용자 인증 (3-legged)
     * @param
     * @return access_token
     */
    public AuthorizeCodeResponseDto authorizeCall(String userId) throws JsonProcessingException {

        String api_uri = testbedProperty.getApiUri();
        String client_id = testbedProperty.getClientId();
        String client_secret = testbedProperty.getClientSecret();
        String uriPath = testbedProperty.getUriPath();
        String callUri = testbedProperty.getLocalUrl();
        String callUriPath = "/auth/authResult";
        String state = RandomStringUtils.randomMix(32);

        try {
            HttpEntity<AuthorizeCodeRequestDto> requestEntity = new HttpEntity<>(null);

            //adding the query params to the URL
            String url = api_uri + "/oauth/2.0/authorize";
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("response_type", "code")
                    .queryParam("client_id", client_id)
                    .queryParam("redirect_uri", callUri + callUriPath)
                    .queryParam("scope", "login inquiry transfer")
                    .queryParam("state", state)
                    .queryParam("client_info", userId)
                    .queryParam("auth_type", 0);

            System.out.println("url.uriBuilder : " + uriBuilder.toUriString());

            ResponseEntity<AuthorizeCodeResponseDto> resoponseEntity = this.testbedRestTemplate.exchange(
                    uriBuilder.toUriString(),
                    HttpMethod.GET,
                    requestEntity,
                    AuthorizeCodeResponseDto.class
            );

            System.out.println("resoponseEntity.hasBody() : " + resoponseEntity.getStatusCode());
            if(resoponseEntity.getStatusCode() == HttpStatus.OK) {
                return resoponseEntity.getBody();
            } else if(resoponseEntity.getStatusCode() == HttpStatus.MOVED_PERMANENTLY) {
                return resoponseEntity.getBody();
            } else {
                return null;
            }
        } catch (Exception e) {
            ObjectMapper mapper = new ObjectMapper();
            String message  = e.getMessage();
            String result = message.substring(message.indexOf("{[}") + 1, message.indexOf("}"));
            AuthorizeCodeResponseDto authorizeCodeResponseDto = mapper.readValue(result, AuthorizeCodeResponseDto.class);
            return authorizeCodeResponseDto;
        }
    }

    /**
     * 사용자 토큰발급 API (3-legged)
     * @return
     */
    public AccessTokenResponseDto accessTokenAuthorizeCall(String authorization_code, String refreshYn) throws JsonProcessingException {
        String api_uri = testbedProperty.getApiUri();
        String client_id = testbedProperty.getClientId();
        String client_secret = testbedProperty.getClientSecret();
        String callUri = testbedProperty.getLocalUrl();
        String callUriPath = "/auth/accessTokenResult";

        try {
            HttpEntity<AccessTokenOobRequestDto> requestEntity = new HttpEntity<>(null);

            //adding the query params to the URL
            String url = api_uri + "/oauth/2.0/token";
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("code", authorization_code)
                    .queryParam("client_id", client_id)
                    .queryParam("client_secret", client_secret)
                    .queryParam("redirect_uri", callUri + callUriPath)
                    .queryParam("grant_type", "authorization_code");    // refresh_token|authorization_code

            System.out.println("url.uriBuilder : " + uriBuilder.toUriString());

            ResponseEntity<AccessTokenResponseDto> resoponseEntity = this.testbedRestTemplate.exchange(
                    uriBuilder.toUriString(),
                    HttpMethod.POST,
                    requestEntity,
                    AccessTokenResponseDto.class
            );

            System.out.println("resoponseEntity.hasBody() : " + resoponseEntity.getStatusCode());
            if(resoponseEntity.getStatusCode() == HttpStatus.OK) {
                return resoponseEntity.getBody();
            } else if(resoponseEntity.getStatusCode() == HttpStatus.MOVED_PERMANENTLY) {
                return resoponseEntity.getBody();
            } else {
                return null;
            }
        } catch (Exception e) {
            ObjectMapper mapper = new ObjectMapper();
            String message  = e.getMessage();
            String result = message.substring(message.indexOf("{[}") + 1, message.indexOf("}"));
            AccessTokenResponseDto AccessTokenResponseDto = mapper.readValue(result, AccessTokenResponseDto.class);
            return AccessTokenResponseDto;
        }
    }

    /**
     * 사용자 토큰발급 API (2-legged)
     * @return
     */
    public AccessTokenResponseDto accessTokenOobCall() throws JsonProcessingException {
        String api_uri = testbedProperty.getApiUri();
        String client_id = testbedProperty.getClientId();
        String client_secret = testbedProperty.getClientSecret();

        try {
            HttpEntity<AccessTokenOobRequestDto> requestEntity = new HttpEntity<>(null);

            //adding the query params to the URL
            String url = api_uri + "/oauth/2.0/token";
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("client_id", client_id)
                    .queryParam("client_secret", client_secret)
                    .queryParam("scope", "oob")
                    .queryParam("grant_type", "client_credentials");

            System.out.println("url.uriBuilder : " + uriBuilder.toUriString());

            ResponseEntity<AccessTokenResponseDto> resoponseEntity = this.testbedRestTemplate.exchange(
                    uriBuilder.toUriString(),
                    HttpMethod.POST,
                    requestEntity,
                    AccessTokenResponseDto.class
            );
            System.out.println("resoponseEntity.getBody() : " + resoponseEntity.getBody());
            System.out.println("resoponseEntity.getStatusCode() : " + resoponseEntity.getStatusCode());
            if(resoponseEntity.getStatusCode() == HttpStatus.OK) {
                return resoponseEntity.getBody();
            } else if(resoponseEntity.getStatusCode() == HttpStatus.MOVED_PERMANENTLY) {
                return resoponseEntity.getBody();
            } else {
                return null;
            }
        } catch (Exception e) {
            ObjectMapper mapper = new ObjectMapper();
            String message  = e.getMessage();
            String result = message.substring(message.indexOf("{[}") + 1, message.indexOf("}"));
            AccessTokenResponseDto AccessTokenResponseDto = mapper.readValue(result, AccessTokenResponseDto.class);
            return AccessTokenResponseDto;
        }
    }
}
