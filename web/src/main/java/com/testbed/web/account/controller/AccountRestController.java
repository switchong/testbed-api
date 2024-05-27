package com.testbed.web.account.controller;

import com.testbed.core.common.testbed.TestbedHttpClient;
import com.testbed.core.domain.testbed.value.Scope;
import com.testbed.core.dto.AccessTokenDto;
import com.testbed.web.account.dto.request.BalanceRequest;
import com.testbed.web.auth.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * /v2.0/account/balance/fin_num
 * /v2.0/account/transaction_list/fin_num
 * AUTHORIZE
 */
@RestController
@AllArgsConstructor
@RequestMapping("/account")
@Slf4j
public class AccountRestController {

    private final TestbedHttpClient testbedHttpClient;
    private final AuthService authService;

    /**
     * 조회 서비스 > 잔액 조회
     * @param balanceRequest
     * @return
     */
    @GetMapping(value = "/balance")
    public Map<String, String> callBalance(@RequestBody BalanceRequest balanceRequest) {
        Map<String, String> result = new HashMap<>();
        String userId = balanceRequest.getUserId();

        if(userId.isEmpty()) {
            result.put("code", "99999");
            result.put("message", "user_id 가 없습니다.");
            return result;
        }
        // token 조회
        AccessTokenDto accessTokenDto = authService.getAccessTokenService(userId, Scope.AUTHORIZE);

        System.out.println("requestParam.uesrId : " + userId);

        result.put("user_id", userId);

        return result;
    }
}
