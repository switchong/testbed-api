package com.testbed.core.common.testbed;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testbed.core.common.testbed.dto.request.AuthorizeCodeRequestDto;
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
     * @param callbackUrl
     * @return
     */
    public String getAuthorizeUrl(String callbackUrl) {
        String api_uri = testbedProperty.getApiUri();
        String client_id = testbedProperty.getClientId();
        String state = RandomStringUtils.randomMix(32);

        String url = api_uri + "/oauth/2.0/authorize";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("response_type", "code")
                .queryParam("client_id", client_id)
                .queryParam("redirect_uri", callbackUrl)
                .queryParam("scope", "login inquiry transfer")
                .queryParam("state", state)
                .queryParam("auth_type", 0);

        String authUrl = uriBuilder.toUriString();

        System.out.println("url.uriBuilder : " + uriBuilder.toUriString());

        return authUrl;
    }

    /**
     * Authorize 사용자 인증 (3-
     * @param
     * @return access_token
     */
    public AuthorizeCodeResponseDto authorizeCall(String callbackUrl) throws JsonProcessingException {

        String api_uri = testbedProperty.getApiUri();
        String client_id = testbedProperty.getClientId();
        String client_secret = testbedProperty.getClientSecret();
        String uriPath = testbedProperty.getUriPath();
        String state = RandomStringUtils.randomMix(32);

        try {
            HttpEntity<AuthorizeCodeRequestDto> requestEntity = new HttpEntity<>(null);

            //adding the query params to the URL
            String url = api_uri + "/oauth/2.0/authorize";
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("response_type", "code")
                    .queryParam("client_id", client_id)
                    .queryParam("redirect_uri", callbackUrl)
                    .queryParam("scope", "login inquiry transfer")
                    .queryParam("state", state)
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
            AuthorizeCodeResponseDto AuthorizeCodeResponseDto = mapper.readValue(result, AuthorizeCodeResponseDto.class);
            return AuthorizeCodeResponseDto;
        }
    }

    /**
     * 사용자 토큰발급 API (3-legged)
     */

}
