package com.testbed.web.auth.contoroller;

import com.testbed.core.common.testbed.TestbedHttpClient;
import com.testbed.web.auth.dto.response.AccessTokenAuthorizeResponse;
import com.testbed.web.auth.dto.response.AuthorizeResponse;
import com.testbed.web.auth.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class AuthRestController {

    private final TestbedHttpClient testbedHttpClient;
    private final AuthService authService;


    @GetMapping(value = "/authorize/{userId}")
    public Map<String, String> AuthorizeCall(@PathVariable("userId") String userId) {
        Map<String, String> result = new HashMap<>();

        System.out.println("requestParam.uesrId : " + userId);

        String callbackUrl = "http://localhost:8080/auth/authResult";

        String url = authService.getAuthorizeUrl(userId, callbackUrl);

//        String response = testbedHttpClient.getAuthorizeUrl(callbackUrl);

        result.put("url", url);
        result.put("user_id", userId);

        return result;
    }

    @GetMapping("/authResult")
    public AuthorizeResponse AuthResult(@RequestParam Map<String, String> paramMap) {
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
        AuthorizeResponse authorizeResponse = authService.getAuthorizeCode(code, scope, state, clientInfo);

        return authorizeResponse;
    }

    /**
     * AccessToken 사용자 토큰발급 API (3-legged)
     * 리다이렉트 URL 결과 처리
     * @param paramMap
     * @return
     */
    @GetMapping("/accessTokenResult")
    public AccessTokenAuthorizeResponse AccessTokenResult(@RequestParam Map<String, String> paramMap) {
        String callbackUrl = "/auth/accessTokenResult";

        AccessTokenAuthorizeResponse tokenAuthResponse = null;

        return tokenAuthResponse;
    }
}
