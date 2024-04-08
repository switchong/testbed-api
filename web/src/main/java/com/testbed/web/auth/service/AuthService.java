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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

@RequiredArgsConstructor
@Service
@Transactional
public class AuthService {

    private final TestbedProperty testbedProperty;

    private final TestbedDbService dbService;

    private final AuthorizeCodeRepository authorizeCodeRepository;
    private final AccessTokenRepository accessTokenRepository;

    /**
     * 사용자 인증 URL
     * @param redirectUri
     * @return
     */
    public String getAuthorizeUrl(String redirectUri) {
        String authUrl = "";
        String uriPath = "/oauth/2.0/authorize";

        String api_url = testbedProperty.getApiUri();
        String client_id = testbedProperty.getClientId();
        String state = RandomStringUtils.randomMix(32);
        String url = api_url + uriPath;
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("response_type", "code")
                .queryParam("client_id", client_id)
                .queryParam("redirect_uri", redirectUri)
                .queryParam("scope", "login inquiry transfer")
                .queryParam("state", state)
                .queryParam("auth_type", 0);

        System.out.println("url.uriBuilder : " + uriBuilder.toUriString());

        ApiLogInDto apiLogInDto = ApiLogInDto.builder()
                .uriId("oauthAuthorize")
                .uriPath(uriPath)
                .method(Method.GET)
                .state(state)
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
    public AuthorizeResponse getAuthorizeCode(String authorizationCode, String scope, String state) {
        scope = "AUTHORIZE";

        AuthorizeResponse authorizeResponse = AuthorizeResponse.builder()
                .code(authorizationCode)
                .scope(scope)
                .state(state)
                .build();

        // DB 저장
        AuthorizeCodeInDto authorizeCodeInDto = AuthorizeCodeInDto.builder()
                .state(authorizeResponse.getState())
                .scope(authorizeResponse.getScope())
                .authorizationCode(authorizeResponse.getCode())
                .build();
        dbService.saveAuthorizeCode(authorizeCodeInDto);

        return authorizeResponse;

    }

    /**
     * state 중복인증체크
     * @param state
     * @return
     */
    public AuthorizeCodeDto chkAuthorizeCode(String state) {
        Long isResult = Long.valueOf(0);
        AuthorizeCodeDto authorizeCodeDto = dbService.findByAuthorizeState(state);

        return authorizeCodeDto;

    }

}
