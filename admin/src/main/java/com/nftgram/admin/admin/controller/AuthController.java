package com.nftgram.admin.admin.controller;

import com.nftgram.admin.common.converter.SHA256Converter;
import com.nftgram.admin.common.util.PasswordUtil;
import com.nftgram.admin.admin.common.request.AdminMemberLoginRequest;
import com.nftgram.admin.admin.common.MemberLoginManager;
import com.nftgram.admin.admin.service.AdminMemberAuthService;
import com.nftgram.core.domain.admin.AdminMember;
import com.nftgram.core.domain.admin.dto.AdminMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.security.GeneralSecurityException;


@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {


    private final MemberLoginManager memberLoginManager;
    private final AdminMemberAuthService adminMemberAuthService;


    /**
     * admin login page
     */
    @GetMapping("/login")
    public String login(Model model) throws GeneralSecurityException, UnsupportedEncodingException{
        model.addAttribute("adminMemberRequest" , new AdminMemberLoginRequest());
        return "auth/login";
    }

    @PostMapping("/login")
    public  String loginProcess(@Valid
                                        AdminMemberLoginRequest request ,
                                BindingResult result,
                                HttpServletResponse response , Model model) throws GeneralSecurityException, UnsupportedEncodingException, UserPrincipalNotFoundException {


        model.addAttribute("adminMemberRequest" , new AdminMemberLoginRequest());
        if (result.hasErrors()){
            return "auth/login";
        }

        AdminMember adminMember = adminMemberAuthService.getAdminMeber(request.getAdminId());

        if (adminMember == null){
            FieldError fieldError = new FieldError("adminMemberRequest" , "adminId", "입력한 관리자 계정 아이디가 존재하지 않습니다");
            result.addError(fieldError);

            return "auth/login";
        }


        String chkPassword = adminMember.getSalt() + request.getPassword();
        String hashPassword = SHA256Converter.getEncSHA256(chkPassword);
        if (!hashPassword.equals(adminMember.getPassword())){
            FieldError fieldError = new FieldError("adminMemberRequest" , "password", "입력한 패스워드가 일치하지 않습니다.");
            result.addError(fieldError);
            return "auth/login";

        }


        AdminMemberDto adminMemberDto = new AdminMemberDto(adminMember.getAId() ,adminMember.getAdminId(), adminMember.getName());
        Cookie adminInfo  =  memberLoginManager.saveLogin(adminMemberDto);
        response.addCookie(adminInfo);


        return "redirect:/";

    }

    /**
     * 로그아웃
     * @param response
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpServletResponse response){
        Cookie expireCookie = memberLoginManager.expire();
        response.addCookie(expireCookie);

        return "redirect:/auth/login";
    }



    /**
     * 관리자 가입 페이지
     * @param model
     * @return
     */
    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("adminMemberRequest", new AdminMemberLoginRequest());
        return "auth/join";
    }


    @PostMapping("/join")
    public String join(@Valid AdminMemberLoginRequest adminJoin,
                       BindingResult result){

        if (result.hasErrors()){
            return "auth/join";
        }

        AdminMember adminMember = adminMemberAuthService.getAdminMeber(adminJoin.getAdminId());
        if (adminMember != null) {
            FieldError fieldError = new FieldError("adminMemberForm","adminId", "관리자 아이디가 중복되었습니다.");
            result.addError(fieldError);
            return "auth/join";
        }

        String salt = PasswordUtil.getRandomSalt(20);
        String password = salt + adminJoin.getPassword();
        String hashPassword = SHA256Converter.getEncSHA256(password);


        Long adminSeq = adminMemberAuthService.addAdminMember(adminJoin.getAdminId(),  hashPassword, adminJoin.getAdminName(), salt);

        if (adminSeq.equals(null)) {
            FieldError fieldError = new FieldError("adminMemberForm","adminId", "회원가입 처리 실패 (확인요망)");
            result.addError(fieldError);
            return "auth/join";
        }

        return "redirect:/auth/login";
    }


    private FieldError customErrorLoginMessage(String field , String message){
        return new FieldError("adminMemberForm" , field , message);
    }
}