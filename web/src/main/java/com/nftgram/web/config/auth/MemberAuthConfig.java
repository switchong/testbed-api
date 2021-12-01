package com.nftgram.web.config.auth;

import com.nftgram.web.common.auth.MemberLoginManager;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Aspect
@Component
@RequiredArgsConstructor
public class MemberAuthConfig {

    private final MemberLoginManager memberLoginManager;

    @Pointcut("execution(* com.nftgram.web.*.*.*Controller.*(..)) || execution(* com.nftgram.web.main.controller.MainController.*(..))")
	public void loginAuth() { }

	@Around("loginAuth()")
    public Object loginCheck(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();

        boolean memberFlag = false;
        String errorMsg = "로그인이 필요합니다.";
        try {
            memberFlag = memberLoginManager.validate();
        } catch (Exception e) {
            memberLoginManager.expire();
        }

        // 로그인정보가 없는 경우
        if ((!request.getRequestURL().toString().matches(".*?(?:\\/auth\\/).*$") &&
             !request.getRequestURL().toString().contains("/error") &&
             !request.getRequestURL().toString().contains("/") &&
             !request.getRequestURL().toString().contains("/index"))
             && memberFlag == false) {

            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter writer;
            writer = response.getWriter();
            writer.print("<script>");
            writer.print("alert('"+errorMsg+"');");
            writer.print("location.href='/auth/nft_login';");
            writer.print("</script>");
            writer.close();
            return writer;
        } else {
            return joinPoint.proceed();
        }
    }

}
