package com.nftgram.admin.common.auth;

import com.nftgram.admin.member.dto.NftMemberAuthDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

@Component
@RequiredArgsConstructor
public class MemberLoginManager {

    private final MemberTokenManager memberTokenManager;

    @Value("${nftgram.cookie.key}")
    private String cookieKey;

    @Value("${nftgram.cookie.domainName}")
    private String domainName;

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
    public NftMemberAuthDto getInfo() throws GeneralSecurityException, UnsupportedEncodingException {
        String memberToken = getToken();
        if (memberToken == null) {
            return NftMemberAuthDto.builder().loginYN("N").build();
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
}
