package com.nftgram.api.config.security;


import com.nftgram.api.common.exception.CAuthenticationEntryPointException;
import com.nftgram.api.config.dto.TokenDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.Base64UrlCodec;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtProvider {



    @Value("spring.jwt.secret")
    private String secretKey;
    private String ROLES = "roles";
    private final  Long accessTokenValidMillisecond =  60  * 60 * 1000L; // 1 hour
    private final  Long refreshTokenValidMillisecond = 14 * 24 * 60 * 60 * 1000L; // 14 day
    private final UserDetailsService userDetailsService;

    @PostConstruct
    protected void init() {
        secretKey  = Base64UrlCodec.BASE64URL.encode(secretKey.getBytes(StandardCharsets.UTF_8));

    }



    public TokenDto createTokenDto(Long userPk , List<String> roles){

        Claims claims = Jwts.claims().setSubject(String.valueOf(userPk));
        claims.put(ROLES , roles);

        Date now = new Date();

        String accessToken = Jwts.builder()
                .setHeaderParam(Header.TYPE , Header.JWT_TYPE)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + accessTokenValidMillisecond))
                .signWith(SignatureAlgorithm.HS256 , secretKey)
                .compact();


        String refreshToken = Jwts.builder()
                .setHeaderParam(Header.TYPE , Header.JWT_TYPE)
                .setExpiration(new Date(now.getTime() + refreshTokenValidMillisecond))
                .signWith(SignatureAlgorithm.HS256  , secretKey)
                .compact();

        return  TokenDto.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessTokenExpireDate(accessTokenValidMillisecond)
                .build();
    }



    public Authentication getAuthentication(String token){

        Claims claims  = parseClaims(token);


        if (claims.get(ROLES) == null){
            throw  new CAuthenticationEntryPointException();
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(claims.getSubject());
        return  new UsernamePasswordAuthenticationToken(userDetails , "" , userDetails.getAuthorities());
    }


    private Claims parseClaims(String token) {

        try {
            return  Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        }catch (ExpiredJwtException e) {
            return  e.getClaims();
        }
    }

    public String resolveToken(HttpServletRequest request) {

        return request.getHeader("X-AUTH-TOKEN");
    }


    public boolean validationToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        }catch (SecurityException  | MalformedJwtException e){
            log.info("잘못된 JWT 서명입니다");
        }catch (ExpiredJwtException e) {
            log.info("만료된 토큰입니다");
        }catch (UnsupportedJwtException e){
            log.info("지원하지 않는 토근입니다 ");
        }catch (IllegalArgumentException e) {
            log.info("잘못된 토큰입니다");
        }
        return false;
    }
}
