package com.testbed.core.common.testbed;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testbed.core.common.testbed.dto.request.AuthorizeRequest;
import com.testbed.core.common.testbed.dto.response.AuthorizeResponse;
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
     * AccessToken 조회
     * @param
     * @return access_token
     */
    public AuthorizeResponse authorizeCall(AuthorizeRequest data) throws JsonProcessingException {

        String api_uri = testbedProperty.getApiUri();
        String client_id = testbedProperty.getClientId();
        String client_secret = testbedProperty.getClientSecret();
        String uriPath = testbedProperty.getUriPath();
        String state = RandomStringUtils.randomMix(32);

        try {
            HttpEntity<AuthorizeRequest> requestEntity = new HttpEntity<>(data);

            //adding the query params to the URL
            String url = this.testbedProperty.getApiUri() + "/oauth/2.0/authorize";
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("response_type", "code")
                    .queryParam("client_id", client_id)
                    .queryParam("redirect_uri", "")
                    .queryParam("scope", "login inquiry transfer")
                    .queryParam("state", state)
                    .queryParam("auth_type", 0);

            ResponseEntity<AuthorizeResponse> resoponseEntity = this.testbedRestTemplate.exchange(
                    uriBuilder.toUriString(),
                    HttpMethod.GET,
                    requestEntity,
                    AuthorizeResponse.class
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
            AuthorizeResponse AuthorizeResponse = mapper.readValue(result, AuthorizeResponse.class);
            return AuthorizeResponse;
        }
    }


}
