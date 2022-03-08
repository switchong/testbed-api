package com.nftgram.admin.admin.common;


import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Aspect
@Component
@RequiredArgsConstructor
public class AdminMemberAuthConfig {


    @Value("${nftgram.cookie.key}")
    private String cookieLoginKey;

    @Pointcut("execution(* com.nftgram.admin.*.*.*Controller.*(..)) || execution(* com.nftgram.admin.admin.controller.AuthController.*(..))")
    public void loginAuth() { }


    @Around("loginAuth()")
    public Object loginCheck(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();

        Cookie[] cookies = request.getCookies();
        String stabyAdminMember = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieLoginKey)) {
                    stabyAdminMember = cookie.getValue();
                }
            }
        }

        // 로그인정보가 없는 경우
        if (stabyAdminMember == null) {

            if( request.getRequestURL().toString().contains("/auth/login") ||
                    request.getRequestURL().toString().contains("/auth/join") ||
                    request.getRequestURL().toString().contains("/auth/logout")	) {
                return joinPoint.proceed();
            }

            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter writer;
            writer = response.getWriter();
            writer.print("<script>");
            writer.print("alert('로그인이 필요합니다.');");
            writer.print("location.href='/auth/login';");
            writer.print("</script>");
            writer.close();
            return writer;
        } else {
            if( request.getRequestURL().toString().contains("/index") ||
                    request.getRequestURL().toString().contains("/auth/login") ||
                    request.getRequestURL().toString().contains("/auth/join") ) {
                return "redirect:/";
            }
            else {
                return joinPoint.proceed();
            }
        }
    }
}
