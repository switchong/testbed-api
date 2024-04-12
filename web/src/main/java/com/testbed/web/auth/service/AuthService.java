package com.testbed.web.auth.service;

import com.testbed.core.common.util.string.RandomStringUtils;
import com.testbed.core.config.testbed.property.TestbedProperty;
import com.testbed.core.domain.testbed.value.Method;
import com.testbed.core.dto.AuthorizeCodeDto;
import com.testbed.core.repository.AccessTokenRepository;
import com.testbed.core.repository.AuthorizeCodeRepository;
import com.testbed.web.auth.dto.response.AuthorizeResponse;
import com.testbed.web.common.dto.request.ApiLogInDto;
import com.testbed.web.common.dto.request.AuthorizeCodeInDto;
import com.testbed.web.common.service.TestbedDbService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Transactional
public class AuthService {

    private final TestbedProperty testbedProperty;
    private String state;

    private final TestbedDbService dbService;

    private final AuthorizeCodeRepository authorizeCodeRepository;
    private final AccessTokenRepository accessTokenRepository;

    /**
     * 사용자 인증 URL
     * @param redirectUri
     * @return
     */
    public String getAuthorizeUrl(String userId, String redirectUri) {
        String authUrl = "";
        String uriPath = "/oauth/2.0/authorize";

        String api_url = testbedProperty.getApiUri();
        String client_id = testbedProperty.getClientId();
        this.state = RandomStringUtils.randomMix(32);
        String url = api_url + uriPath;
        String clientInfo = userId;
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("response_type", "code")
                .queryParam("client_id", client_id)
                .queryParam("redirect_uri", redirectUri)
                .queryParam("scope", "login inquiry transfer")
                .queryParam("state", this.state)
                .queryParam("client_info", userId)
                .queryParam("auth_type", 0);

        System.out.println("url.uriBuilder : " + uriBuilder.toUriString());

        // API_LOG 테이블 로그 저장
        ApiLogInDto apiLogInDto = ApiLogInDto.builder()
                .uriId("oauthAuthorize")
                .uriPath(uriPath)
                .method(Method.GET)
                .state(this.state)
                .request(uriBuilder.toUriString())
                .build();
        dbService.saveApiLog(apiLogInDto);

        authUrl = uriBuilder.toUriString();

        return authUrl;
    }

    /**
     * 사용자 인증 코드 결과 페이지 처리 - login inquiry transfer
     * @param authorizationCode
     * @param state
     * @param scope
     * @return
     */
    public AuthorizeResponse getAuthorizeCode(String authorizationCode, String scope, String state, String clientInfo) {
        AuthorizeResponse authorizeResponse = AuthorizeResponse.builder()
                .code(authorizationCode)
                .scope(scope)
                .state(state)
                .clientInfo(clientInfo)
                .build();
        // Map 정리
        Map<String, String> mapResponse = new HashMap<>();
        mapResponse.put("code", authorizeResponse.getCode());
        mapResponse.put("scope", authorizeResponse.getScope());
        mapResponse.put("state", authorizeResponse.getState());
        mapResponse.put("client_info", authorizeResponse.getClientInfo());

        JSONObject jsonResponse = new JSONObject(mapResponse);

        // API_LOG 테이블 로그 저장
        ApiLogInDto apiLogInDto = ApiLogInDto.builder()
                .state(state)
                .response(jsonResponse.toJSONString())
                .build();
        dbService.updateApiLog(apiLogInDto);

        System.out.print("this.chkAuthorizeCode");
//        if(this.chkAuthorizeCode(state).getAuthorizationCode().isEmpty()) {
            // DB 저장
            AuthorizeCodeInDto authorizeCodeInDto = AuthorizeCodeInDto.builder()
                    .userId(authorizeResponse.getClientInfo())
                    .state(authorizeResponse.getState())
                    .scope(authorizeResponse.getScope())
                    .authorizationCode(authorizeResponse.getCode())
                    .build();
            dbService.saveAuthorizeCode(authorizeCodeInDto);

            System.out.print("this.chkAuthorizeCode END");
//        }

        return authorizeResponse;

    }

    /**
     * state 중복인증체크
     * @param state
     * @return
     */
    public AuthorizeCodeDto chkAuthorizeCode(String state) {
        AuthorizeCodeDto authorizeCodeDto = dbService.findByAuthorizeState(state);

        return authorizeCodeDto;

    }

}
