package com.testbed.web.oob.controller;

import com.testbed.core.common.testbed.TestbedHttpClient;
import com.testbed.core.domain.testbed.value.Scope;
import com.testbed.core.dto.AccessTokenDto;
import com.testbed.web.auth.service.AuthService;
import com.testbed.web.oob.dto.request.InquiryRealNameWebRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 *  조회서비스 (이용기관)
 *  계좌실명조회 	https://openapi.openbanking.or.kr/v2.0/inquiry/real_name 	/v2.0/inquiry/real_name
 * 	송금인정보조회 	https://openapi.openbanking.or.kr/v2.0/inquiry/remit_list 	/v2.0/inquiry/remit_list
 * 	수취조회 	https://openapi.openbanking.or.kr/v2.0/inquiry/receive 	/v2.0/inquiry/receive
 */
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/oob")
public class OobRestController {

    private final TestbedHttpClient testbedHttpClient;
    private final AuthService authService;

    /**
     * 계좌실명조회 - /v2.0/inquiry/real_name
     * "bank_tran_id":"F123456789U4BC34239Z",
     * "bank_code_std":"097",
     * "account_num":"1101230000678",
     * "account_holder_info_type":" ",
     * "account_holder_info":"880101",
     * "tran_dtime":"20190910101921"
     * @return
     */
    @PostMapping(name="/inquiryRealName", produces = "application/json")
    public Map<String, String> callInquiryRealName(@RequestBody InquiryRealNameWebRequest inquiryRealNameWebRequest) {
        Map<String, String> result = new HashMap<>();

        String userId = inquiryRealNameWebRequest.getUserId();
        String token = null;

        // 토큰 조회
        AccessTokenDto accessTokenDto = authService.getAccessTokenService(userId, Scope.OOB);
        if(accessTokenDto == null) {
            result.put("code", "10001");
            result.put("message", "Token이 없습니다.");
            return result;
        } else if(accessTokenDto.getExpiresDate().isAfter(LocalDateTime.now())) {
            result.put("code", "10002");
            result.put("message", "Token이 만료되었습니다.");
            return result;
        } else {
            token = accessTokenDto.getAccessToken();
        }

        return result;
    }

    /**
     * 송금인정보조회 - /v2.0/inquiry/remit_list
     * @return
     */
    @PostMapping("/inquiryRemitList")
    public Map<String, String> callInquiryRemitList() {
        Map<String, String> result = new HashMap<>();

        return result;
    }

    /**
     * 수취조회 - /v2.0/inquiry/receive
     * @return
     */
    @PostMapping("/inquiryReceive")
    public Map<String, String> callInquiryReceive() {
        Map<String, String> result = new HashMap<>();

        return result;
    }
}
