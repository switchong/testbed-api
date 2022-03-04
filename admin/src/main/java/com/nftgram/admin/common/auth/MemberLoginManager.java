package com.nftgram.admin.common.auth;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.nftgram.admin.admin.dto.AdminMemberAuthDto;
import com.nftgram.admin.config.security.AES256Converter;
import com.nftgram.core.domain.admin.dto.AdminMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

@Component
@RequiredArgsConstructor
public class MemberLoginManager {

    private final MemberTokenManager memberTokenManager;

//    @Value("${nftgram.cookie.key}")
//    private String cookieKey;
//
//    @Value("${nftgram.cookie.domainName}")
//    private String domainName;

    private static String cookieKey;

    @Value("${nftgram.cookie.key}")
    public void setCookieKey(String cookieKey) {
        this.cookieKey = cookieKey;
    }

    private static String domainName;

    @Value("${nftgram.cookie.domainName}")
    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    /**
     * 로그인 쿠키정보 저장
     * @return
     */
    public Cookie save(String token, boolean autoLoginFlag) {
        int expireTime = 60*60*24; // 1일 유지
        if (autoLoginFlag) {
            expireTime = 40*365*24*60*60; // 영구 저장
        }

        Cookie memberCookie = new Cookie(cookieKey, token);
        memberCookie.setPath("/");
        memberCookie.setDomain(domainName);
        memberCookie.setMaxAge(expireTime);

        return memberCookie;
    }

    /**
     * 로그인 쿠키정보 삭제
     * @return
     */
    public Cookie expire() {
        Cookie memberCookie = new Cookie(cookieKey, null);
        memberCookie.setPath("/");
        memberCookie.setDomain(domainName);
        memberCookie.setMaxAge(0);

        return memberCookie;
    }

    /**
     * 로그인 정보 가져오기
     * @return
     */
    public AdminMemberAuthDto getInfo() throws GeneralSecurityException, UnsupportedEncodingException {
        String memberToken = getToken();
        if (memberToken == null) {
            return AdminMemberAuthDto.builder().loginYN("N").build();
        }
        return memberTokenManager.getTokenToMember(memberToken);
    }

    /**
     * 로그인 토큰 정보 검증
     * @return
     * @throws GeneralSecurityException
     * @throws UnsupportedEncodingException
     */
    public boolean validate() throws GeneralSecurityException, UnsupportedEncodingException {
        String memberToken = getToken();
        if (memberToken == null) {
            return false;
        }
        return true;
    }

    /**
     * 로그인 토큰 복호화 정보 가져오기 
     * @return
     */
    private String getToken() {
        HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        Cookie[] cookies = request.getCookies();
        String memberToken = null;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(cookieKey)) {
					memberToken = cookie.getValue();
				}
			}
		}

		if (StringUtils.isEmpty(memberToken))
        {
            return null;
        }

		return memberToken;
    }


    public static Cookie saveLogin(AdminMemberDto adminMemberDto) {

        JsonFactory factory = new JsonFactory();
        StringWriter jsonObjectWriter = new StringWriter();

        try {
            JsonGenerator generator = factory.createGenerator(jsonObjectWriter);
            generator.writeStartObject();
            generator.writeStringField("adminId", adminMemberDto.getAdminId());
            generator.writeEndObject();
            generator.close();

            String encodeLoginData = AES256Converter.encode(jsonObjectWriter.toString());

            Cookie adminCookie = new Cookie(cookieKey, encodeLoginData);
            adminCookie.setPath("/");
            adminCookie.setDomain(domainName);
            adminCookie.setMaxAge(60*60*24); // 1일 유지

            return adminCookie;
        } catch ( GeneralSecurityException | IOException e) {
            throw new IllegalStateException("로그인 정보 쿠키 생성 실패");
        }
    }
}
