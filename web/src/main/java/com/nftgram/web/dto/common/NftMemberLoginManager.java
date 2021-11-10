package com.nftgram.web.dto.common;


import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class NftMemberLoginManager {


    @Value("${nftgram.cookie.key}")
    private String cookieKey;


    @Value("${nftgram.cookie.domainName}")
    private String domainName;

    /**
     * 로그인 쿠키정보 저장
     * @return
     */
    public Cookie save(String token , boolean autoLogin){

        int expireTiem = 60*60*24; //1day
        if (autoLogin){
            expireTiem =  40*365*24*60*60; // 영구 저장
        }

        Cookie memberCookie = new Cookie(cookieKey , token);
        memberCookie.setPath("/");
        memberCookie.setDomain(domainName);
        memberCookie.setMaxAge(expireTiem);

        return memberCookie;
    }

    public Cookie expire(){
        Cookie memberCookie = new Cookie(cookieKey , null);
        memberCookie.setPath("/");
        memberCookie.setDomain(domainName);
        memberCookie.setMaxAge(0);

        return memberCookie;
    }


    private String getToken(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        Cookie[] cookies = request.getCookies();
        String memberToken = null;
        if (cookies != null){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals(cookieKey)){
                    memberToken = cookie.getValue();
                }
            }
        }
        if (StringUtils.isEmpty(memberToken)){
            return  null;
        }

        return memberToken;
    }
}
