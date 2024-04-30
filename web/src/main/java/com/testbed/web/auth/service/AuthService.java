package com.testbed.web.auth.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testbed.core.common.testbed.TestbedHttpClient;
import com.testbed.core.common.testbed.dto.response.AccessTokenResponseDto;
import com.testbed.core.common.util.string.RandomStringUtils;
import com.testbed.core.config.testbed.property.TestbedProperty;
import com.testbed.core.domain.testbed.AccessToken;
import com.testbed.core.domain.testbed.AuthorizeCode;
import com.testbed.core.domain.testbed.value.Method;
import com.testbed.core.domain.testbed.value.Scope;
import com.testbed.core.dto.AccessTokenDto;
import com.testbed.core.dto.AuthorizeCodeDto;
import com.testbed.core.repository.AccessTokenRepository;
import com.testbed.core.repository.AuthorizeCodeRepository;
import com.testbed.web.auth.dto.response.AccessTokenResponse;
import com.testbed.web.auth.dto.response.AuthorizeResponse;
import com.testbed.web.common.dto.request.AccessTokenInDto;
import com.testbed.web.common.dto.request.ApiLogInDto;
import com.testbed.web.common.dto.request.AuthorizeCodeInDto;
import com.testbed.web.common.service.TestbedDbService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Transactional
public class AuthService {

    private final TestbedHttpClient testbedHttpClient;
    private final TestbedProperty testbedProperty;
    private String state;

    private final TestbedDbService dbService;

    private final AuthorizeCodeRepository authorizeCodeRepository;
    private final AccessTokenRepository accessTokenRepository;

    /**
     * 사용자 인증 URL
     * @param userId
     * @return
     */
    public String getAuthorizeUrl(String userId) {
        String uriPath = "/oauth/2.0/authorize";
        String state = RandomStringUtils.randomMix(32);

        String authUrl = testbedHttpClient.getAuthorizeUrl(userId, state, uriPath);

        System.out.println("testbedHttpClient.getAuthorizeUrl >>>> " + authUrl);

        // API_LOG 테이블 로그 저장
        ApiLogInDto apiLogInDto = ApiLogInDto.builder()
                .uriId("oauthAuthorize")
                .uriPath(uriPath)
                .method(Method.GET)
                .state(state)
                .request(authUrl)
                .build();
        dbService.saveApiLog(apiLogInDto);

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
        String tokenUrl = testbedProperty.getLocalUrl() + "/auth/accessToken";
        AuthorizeResponse authorizeResponse = AuthorizeResponse.builder()
                .code(authorizationCode)
                .scope(scope)
                .state(state)
                .userId(clientInfo)
                .tokenUrl(tokenUrl)
                .tokenScope("AUTHORIZE")
                .build();
        // Map 정리
        Map<String, String> mapResponse = new HashMap<>();
        mapResponse.put("code", authorizeResponse.getCode());
        mapResponse.put("scope", authorizeResponse.getScope());
        mapResponse.put("state", authorizeResponse.getState());
        mapResponse.put("client_info", authorizeResponse.getUserId());

        JSONObject jsonResponse = new JSONObject(mapResponse);

        // API_LOG 테이블 로그 저장
        ApiLogInDto apiLogInDto = ApiLogInDto.builder()
                .state(state)
                .response(jsonResponse.toJSONString())
                .build();
        dbService.updateApiLog(apiLogInDto);

        System.out.print("this.chkAuthorizeCode"+this.chkAuthorizeCode(state).getAuthorizationCode());
        if(this.chkAuthorizeCode(state).getAuthorizationCode() == null) {
            // DB 저장
            AuthorizeCodeInDto authorizeCodeInDto = AuthorizeCodeInDto.builder()
                    .userId(authorizeResponse.getUserId())
                    .state(authorizeResponse.getState())
                    .scope(authorizeResponse.getScope())
                    .authorizationCode(authorizeResponse.getCode())
                    .build();
            dbService.saveAuthorizeCode(authorizeCodeInDto);

            System.out.print("this.chkAuthorizeCode END");
        }

        return authorizeResponse;

    }

    /**
     * state 중복인증체크
     * @param state
     * @return
     */
    public AuthorizeCodeDto chkAuthorizeCode(String state) {
        AuthorizeCode authorizeCode = dbService.findByAuthorizeState(state);


        String authorizationCode = null;
        if(authorizeCode != null) {
            authorizationCode = authorizeCode.getAuthorizationCode();
            state = authorizeCode.getState();
        }
        AuthorizeCodeDto authorizeCodeDto = AuthorizeCodeDto.builder()
                .state(state)
                .authorizationCode(authorizationCode)
                .build();

        return authorizeCodeDto;

    }

    /**
     * 토큰 DB 조회
     * @param userId
     * @param scope
     * @return
     */
    public AccessTokenDto getAccessTokenService(String userId, Scope scope) {
        // DB 조회
        AccessToken dbio = dbService.findByAccessToken(userId, scope);

        System.out.print("findByAccessToken >>>>>>>>>>>>> " + dbio);

        AccessTokenDto accessTokenDto = null;

        if(dbio == null) {

        } else {
            accessTokenDto = AccessTokenDto.builder()
                    .accessToken(dbio.getAccessToken())
                    .scope(dbio.getScope())
                    .expiresDate(dbio.getExpiresDate())
                    .build();

        }

        System.out.print("accessTokenDto >>>>>>>>>>> " + accessTokenDto);

        return accessTokenDto;
    }

    /**
     * Token 2-legged 발급
     * @param userId
     * @return
     * @throws JsonProcessingException
     */
    public AccessTokenResponse callAccessTokenOob(String userId, Scope scope) throws JsonProcessingException{
        AccessTokenResponseDto accessTokenResponseDto = testbedHttpClient.accessTokenOobCall();

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiresDate = LocalDateTime.now();
        if(accessTokenResponseDto.getExpiresIn().intValue() > 0) {
            expiresDate = now.minusSeconds(accessTokenResponseDto.getExpiresIn());
        }

        AccessTokenResponse accessTokenResponse = AccessTokenResponse.builder()
                .userId(userId)
                .accessToken(accessTokenResponseDto.getAccessToken())
                .scope(scope)
                .expiresDate(expiresDate)
                .build();

        // DB 저장
        AccessTokenInDto accessTokenInDto = AccessTokenInDto.builder()
                .userId(userId)
                .accessToken(accessTokenResponseDto.getAccessToken())
                .expiresIn(accessTokenResponseDto.getExpiresIn())
                .scope(scope)
                .build();
        dbService.saveAccessToken(accessTokenInDto);

        // Map 정리
        // ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        Map mapResponse = objectMapper.convertValue(accessTokenResponseDto, Map.class);

        Map<String, String> mapRequest = new HashMap<>();
        mapRequest.put("client_id", testbedProperty.getClientId());
        mapRequest.put("client_secret", testbedProperty.getClientSecret());
        mapRequest.put("scope", "oob");
        mapRequest.put("grant_type", "client_credentials");

        JSONObject jsonRequest = new JSONObject(mapRequest);
        JSONObject jsonResponse = new JSONObject(mapResponse);

        // API_LOG 테이블 로그 저장
        ApiLogInDto apiLogInDto = ApiLogInDto.builder()
                .uriId("oauthToken")
                .uriPath("/oauth/2.0/token")
                .method(Method.POST)
                .state("")
                .request(jsonRequest.toString())
                .response(jsonResponse.toString())
                .build();
        dbService.saveApiLog(apiLogInDto);

        return accessTokenResponse;
    }

    public AccessTokenResponse callAccessTokenAuthorize(String userId, String authorization_code, String refreshYn) throws JsonProcessingException{
        Scope scope = Scope.AUTHORIZE;
        AccessTokenResponseDto accessTokenResponseDto = testbedHttpClient.accessTokenAuthorizeCall(authorization_code, refreshYn);

//        System.out.print("accessTokenResponseDto >>>>>> " + new JSONObject((Map) accessTokenResponseDto));

        AccessTokenResponse accessTokenResponse = null;

        if(accessTokenResponseDto.getRspCode() != null) {
            accessTokenResponse = AccessTokenResponse.builder()
                    .rspCode(accessTokenResponseDto.getRspCode())
                    .rspMessage(accessTokenResponseDto.getRspMessage())
                    .build();
        } else {
            accessTokenResponse = AccessTokenResponse.builder()
                    .userId(userId)
                    .accessToken(accessTokenResponseDto.getAccessToken())
                    .scope(scope)
                    .build();

            // DB 저장
            AccessTokenInDto accessTokenInDto = AccessTokenInDto.builder()
                    .userId(userId)
                    .accessToken(accessTokenResponseDto.getAccessToken())
                    .expiresIn(accessTokenResponseDto.getExpiresIn())
                    .scope(scope)
                    .build();
            dbService.saveAccessToken(accessTokenInDto);

            // Map 정리
            // ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();
            Map mapResponse = objectMapper.convertValue(accessTokenResponseDto, Map.class);

            Map<String, String> mapRequest = new HashMap<>();
            mapRequest.put("client_id", testbedProperty.getClientId());
            mapRequest.put("client_secret", testbedProperty.getClientSecret());
            mapRequest.put("scope", "authorize");
            mapRequest.put("grant_type", "authorization_code");

            JSONObject jsonRequest = new JSONObject(mapRequest);
            JSONObject jsonResponse = new JSONObject(mapResponse);

            // API_LOG 테이블 로그 저장
            ApiLogInDto apiLogInDto = ApiLogInDto.builder()
                    .uriId("oauthToken")
                    .uriPath("/oauth/2.0/token")
                    .method(Method.POST)
                    .state("")
                    .request(jsonRequest.toString())
                    .response(jsonResponse.toString())
                    .build();
            dbService.saveApiLog(apiLogInDto);
        }

        return accessTokenResponse;
    }

}
