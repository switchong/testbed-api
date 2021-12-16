package com.nftgram.core.common.security;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Random;

public abstract class NftSecurityHandler {

    public abstract String randomSalt();

    public String getMobile() {
        HttpServletRequest request
                =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String[] mobileList = {"iPhone", "iPod", "Android", "BlackBerry", "Windows CE", "Nokia", "Webos",
                "Opera Mini", "SonyEricsson", "Opera Mobi", "IEMobile", "blazer", "compal"};

        String findMobile = Arrays.stream(mobileList)
                .filter(mobile -> mobile.equals(request.getHeader("USER-AGENT")))
                .findFirst()
                .orElse(null);

        return findMobile;
    }

    public String getRandomPassword(int length){
        char[] randomChars = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s',
                't','u','v','w','x','y','z','0','1','2','3','4','5','6','7','8','9'};
        StringBuffer sb = new StringBuffer();
        Random rn = new Random();
        for( int i = 0 ; i < length ; i++ ){

            sb.append( randomChars[ rn.nextInt( randomChars.length ) ] );
        }
        return sb.toString();
    }

    public String getRemoteAddr() {
        HttpServletRequest request
                =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-RealIP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }

}
