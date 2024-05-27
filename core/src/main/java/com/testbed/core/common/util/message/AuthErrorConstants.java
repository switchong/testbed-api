package com.testbed.core.common.util.message;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AuthErrorConstants {

    public static final Map<String, String> ERROR_AUTH_CODE = new HashMap<>();
    static {
        ERROR_AUTH_CODE.put("119" , "인증에 필요한 헤더 값이 존재하지 않을 때");
        ERROR_AUTH_CODE.put("801" , "사용자일련번호와 token의 사용자가 맞지 않을 때");
        ERROR_AUTH_CODE.put("803" , "인증요청 시 사용자 세션 값과 인증코드발급 시 사용자 세션 값이 맞지 않을 때");
        ERROR_AUTH_CODE.put("804" , "사용자가 인증 페이지에서 동의하지 않을 때");
        ERROR_AUTH_CODE.put("992" , "Http Header에 Authorization Bearer access token이 없을 때");
        ERROR_AUTH_CODE.put("3000103" , "인증에 필요한 필수 파라미터 값이 존재하지 않을 때 or 특정 파라미터 값이 중복으로 들어왔을 때");
        ERROR_AUTH_CODE.put("3000201" , "client_id가 존재하지 않을 때 or key/secret이 맞지 않을 때 or 비활성화되어 사용할 수 없을 때");
        ERROR_AUTH_CODE.put("3000113" , "유효하지 않은 authorization code (만료 or 기사용)");
        ERROR_AUTH_CODE.put("3000114" , "등록한 callback url과 요청한 callback url이 맞지 않을 때");
        ERROR_AUTH_CODE.put("3000115" , "scope이 맞지 않을 때(기본 제공하는 scope명[oob|login|inquiry|transfer|sa]이 맞지 않을 때)");
        ERROR_AUTH_CODE.put("3000116" , "response_type 유형이 맞지 않을 때");
        ERROR_AUTH_CODE.put("3000117" , "grant_type이 맞지 않을 때");
        ERROR_AUTH_CODE.put("3002110" , "만료된 session 값을 사용하였을 때");

    }
}
