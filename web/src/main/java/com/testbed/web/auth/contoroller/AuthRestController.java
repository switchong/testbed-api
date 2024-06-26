package com.testbed.web.auth.contoroller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.testbed.core.common.testbed.TestbedHttpClient;
import com.testbed.core.domain.testbed.value.Scope;
import com.testbed.core.dto.AccessTokenDto;
import com.testbed.web.auth.dto.request.AccessTokenWebRequest;
import com.testbed.web.auth.dto.response.AccessTokenAuthorizeWebResponse;
import com.testbed.web.auth.dto.response.AccessTokenWebResponse;
import com.testbed.web.auth.dto.response.AuthorizeWebResponse;
import com.testbed.web.auth.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class AuthRestController {

    private final TestbedHttpClient testbedHttpClient;
    private final AuthService authService;

    @GetMapping(value = "/authorize")
    public Map<String, String> AuthorizeCall() {
        Map<String, String> result = new HashMap<>();

        result.put("code", "99999");
        result.put("message", "uri path 내에 /user_id 가 없습니다.");
        return result;
    }

    @GetMapping(value = "/authorize/{userId}")
    public Map<String, String> AuthorizeCall(@PathVariable("userId") String userId) {
        Map<String, String> result = new HashMap<>();

        if(userId.isEmpty()) {
            result.put("code", "99999");
            result.put("message", "user_id 가 없습니다.");
            return result;
        }

        System.out.println("requestParam.uesrId : " + userId);

        String url = authService.getAuthorizeUrl(userId);

//        String response = testbedHttpClient.getAuthorizeUrl(callbackUrl);

        result.put("url", url);
        result.put("user_id", userId);

        return result;
    }

    @GetMapping(value="/authResult")
    public AuthorizeWebResponse AuthResult(@RequestParam Map<String, String> paramMap) {
        String strResult = "";
        String code = paramMap.get("code");
        String scope = paramMap.get("scope");
        String state = paramMap.get("state");
        String clientInfo = paramMap.get(("client_info"));
        if(!scope.equals("oob")) {
            scope = "AUTHORIZE";
        } else {
            scope = "OOB";
        }

        // 중복체크
        AuthorizeWebResponse authorizeWebResponse = authService.getAuthorizeCode(code, scope, state, clientInfo);

        return authorizeWebResponse;
    }

    @PostMapping(value="/accessToken", produces = "application/json")
    public AccessTokenWebResponse AccessTokenCall(@RequestBody AccessTokenWebRequest accessTokenRequest) throws JsonProcessingException {
        String userId = accessTokenRequest.getUserId();
        Scope scope = (accessTokenRequest.getScope().equals("OOB")) ? Scope.OOB : Scope.AUTHORIZE;
        String authorization_code = accessTokenRequest.getCode();   // AUTHORIZE 때만.

        // 토큰 조회
        AccessTokenDto accessTokenDto = authService.getAccessTokenService(userId, scope);

        System.out.print("AccessToken >>>> " + accessTokenDto);

        AccessTokenWebResponse accessTokenWebResponse = null;

        if(scope.equals(Scope.OOB)) {
            if(accessTokenDto == null) {
                accessTokenWebResponse = authService.callAccessTokenOob(userId, scope);
            } else {
                if(accessTokenDto.getExpiresDate().isAfter(LocalDateTime.now())) {
                    accessTokenWebResponse = authService.callAccessTokenOob(userId, scope);
                } else {
                    accessTokenWebResponse = AccessTokenWebResponse.builder()
                            .userId(userId)
                            .accessToken(accessTokenDto.getAccessToken())
                            .scope(accessTokenDto.getScope())
                            .expiresDate(accessTokenDto.getExpiresDate())
                            .build();
                }
            }
        } else if(scope.equals(Scope.AUTHORIZE)){
            if(accessTokenDto == null) {
                accessTokenWebResponse = authService.callAccessTokenAuthorize(userId, authorization_code, "N");
            } else {
                if(accessTokenDto.getExpiresDate().isAfter(LocalDateTime.now())) {
                    accessTokenWebResponse = authService.callAccessTokenAuthorize(userId, authorization_code, "N");
                } else{
                    accessTokenWebResponse = AccessTokenWebResponse.builder()
                            .userId(userId)
                            .accessToken(accessTokenDto.getAccessToken())
                            .scope(accessTokenDto.getScope())
                            .expiresDate(accessTokenDto.getExpiresDate())
                            .build();
                }
            }
        } else {
            accessTokenWebResponse = AccessTokenWebResponse.builder().build();
        }

        return accessTokenWebResponse;
    }



    /**
     * AccessToken 사용자 토큰발급 API 결과(3-legged)
     * 리다이렉트 URL 결과 처리
     * @param paramMap
     * @return
     */
    @GetMapping("/accessTokenResult")
    public AccessTokenAuthorizeWebResponse AccessTokenResult(@RequestParam Map<String, String> paramMap) {
        String callbackUrl = "/auth/accessTokenResult";

        JSONObject jsonResponse = new JSONObject(paramMap);

        System.out.print("jsonResponse >>>>> " + jsonResponse);


        AccessTokenAuthorizeWebResponse tokenAuthResponse = null;

        return tokenAuthResponse;
    }
}
